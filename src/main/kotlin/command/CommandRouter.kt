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
            println("$commandKey: 잘못된 값을 받았습니다.")
        }

        return result
    }

    private fun invalidCommand(input: String): Command.Result {
        println("알 수 없는 입력 '$input'. 다시 시도해주세요.")

        return Command.Result.invalid()
    }
}
