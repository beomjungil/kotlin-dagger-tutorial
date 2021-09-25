
import command.CommandProcessor
import command.DaggerCommandProcessorFactory
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val commandRouterFactory = DaggerCommandProcessorFactory.create()
    val commandProcessor: CommandProcessor = commandRouterFactory.processor()
    val startMessage = """
    |   ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
    |              A  T  M
    |   ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
""".trimMargin("|")
    println(startMessage)

    while (scanner.hasNextLine()) {
        commandProcessor.process(scanner.nextLine())
    }
}