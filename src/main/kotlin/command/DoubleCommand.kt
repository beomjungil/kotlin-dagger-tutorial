package command

import outputter.Outputter
import javax.inject.Inject


abstract class DoubleCommand constructor(
    private val outputter: Outputter
): SingleArgCommand() {
    override fun handleArg(arg: String): Command.Result {
        val amount = arg.toDouble()

        if (amount == null) {
            outputter.output("'$arg'은 잘못된 숫자입니다.");
        } else if (amount <= 0) {
            outputter.output("amount는 0보다 커야합니다.")
        } else {
            handleAmount(amount)
        }
        return Command.Result.handled()
    }

    protected abstract fun handleAmount(amount: Double)
}