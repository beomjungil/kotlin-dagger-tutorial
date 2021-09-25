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
            outputter.output("'$loggedInUser' is already logged in")
            if (loggedInUser != username) {
                outputter.output("run `logout` first before trying to log in another user")
            }
            return Command.Result.handled()
        }
        val account = database.getAccount(username)

        outputter.output("Welcome, ${account.username}. Your balance is: ${account.balance}")
        return Command.Result.enterNestedCommandSet(
            userCommandsRouterFactory
                .create(account.username)
                .router()
        )
    }

    override val key: String = "login"
}