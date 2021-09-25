package command

import outputter.Outputter
import javax.inject.Inject


abstract class DoubleCommand constructor(
    private val outputter: Outputter
): SingleArgCommand() {
    override fun handleArg(arg: String): Command.Result {
        val amount = arg.toDouble()

        if (amount == null) {
            outputter.output("'$arg' is not a valid number")
        } else if (amount <= 0) {
            outputter.output("amount must be positive")
        } else {
            handleAmount(amount)
        }
        return Command.Result.handled()
    }

    protected abstract fun handleAmount(amount: Double)
}