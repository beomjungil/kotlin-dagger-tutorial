package logout

import command.Command
import outputter.Outputter
import javax.inject.Inject

class LogoutCommand @Inject constructor(
    private val outputter: Outputter
    ): Command {
    override fun handleInput(input: List<String>): Command.Result {
        return if (input.isEmpty()) {
            outputter.output("로그아웃 했습니다.")
            Command.Result.inputCompleted()
        } else {
            Command.Result.invalid()
        }
    }

    override val key: String = "로그아웃"
}