package helloworld

import command.Command
import outputter.Outputter
import javax.inject.Inject

class HelloWorldCommand @Inject constructor(private val outputter: Outputter): Command {
    override fun handleInput(input: List<String>): Command.Result {
        outputter.output("World!")
        return Command.Result.handled()
    }

    override val key: String = "hello"
}