package login

import command.Command
import command.SingleArgCommand
import database.Database
import outputter.Outputter
import user.UserCommandsRouter
import java.util.*
import javax.inject.Inject

class LoginCommand @Inject constructor(
    private val database: Database,
    private val outputter: Outputter,
    private val account: Optional<Database.Account>,
    private val userCommandsRouterFactory: UserCommandsRouter.Factory,
) : SingleArgCommand() {
    override fun handleArg(username: String): Command.Result {
        if (account.isPresent) {
            val loggedInUser = account.get().username
            outputter.output("현재 '$loggedInUser' 님으로 로그인 했습니다.")
            if (loggedInUser != username) {
                outputter.output("로그아웃 후 다른 사용자로 로그인하세요.")
            }
            return Command.Result.handled()
        }
        val account = database.getAccount(username)

        outputter.output("${account.username} 님, 반갑습니다. 현재 잔액은 '${account.balance}' 입니다.")
        return Command.Result.enterNestedCommandSet(
            userCommandsRouterFactory
                .create(account.username)
                .router()
        )
    }

    override val key: String = "로그인"
}