package command

interface Command {
    enum class Status {
        INVALID,
        HANDLED,
        INPUT_COMPLETED
    }

    val key: String

    fun handleInput(input: List<String>): Result

    class Result constructor(
        val status: Status,
        val nestedCommandRouter: CommandRouter? = null
    ) {
        companion object {
            fun enterNestedCommandSet(nestedCommandRouter: CommandRouter): Result {
                return Result(
                    status = Status.HANDLED,
                    nestedCommandRouter = nestedCommandRouter
                )
            }

            fun handled(): Result {
                return Result(Status.HANDLED)
            }

            fun inputCompleted(): Result {
                return Result(Status.INPUT_COMPLETED)
            }

            fun invalid(): Result {
                return Result(Status.INVALID)
            }
        }
    }
}