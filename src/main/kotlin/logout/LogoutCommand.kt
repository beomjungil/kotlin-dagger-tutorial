package logout

import command.Command
import outputter.Outputter
import javax.inject.Inject

class LogoutCommand @Inject constructor(
    private val outputter: Outputter
    ): Command {
    override fun handleInput(input: List<String>): Command.Result {
        return if (input.isEmpty()) {
            outputter.output("logged out.")
            Command.Result.inputCompleted()
        } else {
            Command.Result.invalid()
        }
    }

    override val key: String = "logout"
}