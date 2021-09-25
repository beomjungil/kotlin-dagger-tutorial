package command

import javax.inject.Inject

@JvmSuppressWildcards
class CommandRouter @Inject constructor(private val commands: Map<String, Command>) {
    fun route(input: String): Command.Result {
        val splitInput = input.split(" ")

        if (splitInput.isEmpty()) {
            return invalidCommand(input)
        }

        val commandKey = splitInput.first()
        val command = commands[commandKey] ?: return invalidCommand(input)
        val result = command.handleInput(splitInput.subList(1, splitInput.size))

        if (result.status == Command.Status.INVALID) {
            println("Invalid usage of \"$commandKey\". please try again.")
        }

        return result
    }

    private fun invalidCommand(input: String): Command.Result {
        println("Couldn't understand \"$input\". please try again.")

        return Command.Result.invalid()
    }
}
