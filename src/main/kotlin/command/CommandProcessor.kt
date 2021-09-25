package command

import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import java.util.ArrayDeque

@Singleton
class CommandProcessor @Inject constructor(
    private val firstCommandRouter: CommandRouter
) {
    private val commandRouterStack: Deque<CommandRouter> = ArrayDeque()

    init {
        commandRouterStack.push(firstCommandRouter)
    }

    fun process(input: String): Command.Status {
        val result = commandRouterStack.peek().route(input)

        if (result.status == Command.Status.INPUT_COMPLETED) {
            commandRouterStack.pop()

            return if (commandRouterStack.isEmpty()) Command.Status.INPUT_COMPLETED else Command.Status.HANDLED
        }

        result.nestedCommandRouter?.let {
            commandRouterStack.push(it)
        }

        return result.status
    }
}