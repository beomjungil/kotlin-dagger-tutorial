package helloworld

import command.Command
import outputter.Outputter
import javax.inject.Inject

class HelloWorldCommand @Inject constructor(private val outputter: Outputter): Command {
    override fun handleInput(input: List<String>): Command.Result {
        outputter.output("세상아!")
        return Command.Result.handled()
    }

    override val key: String = "안녕"
}