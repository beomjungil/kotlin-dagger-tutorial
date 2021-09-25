package withdraw

import amounts.MinimumBalance
import command.DoubleCommand
import database.Database
import outputter.Outputter
import javax.inject.Inject


class WithdrawCommand @Inject constructor(
    private val outputter: Outputter,
    private val account: Database.Account,
    @MinimumBalance private val minimumBalance: Double,
    private val withdrawalLimiter: WithdrawalLimiter,
): DoubleCommand(outputter) {
    override fun handleAmount(amount: Double) {
        val remainingWithdrawalLimit = withdrawalLimiter.remainingWithdrawalLimit

        if ((amount - remainingWithdrawalLimit) > 0) {
            outputter.output("You may not withdraw '$amount'; You may withdraw '$remainingWithdrawalLimit' more in this session.")
            return
        }

        val newBalance = account.balance - amount

        if ((newBalance - minimumBalance) < 0) {
            outputter.output(
                "You don't have sufficient funds to withdraw '$amount'. your balance is '${account.balance}' and the minimum balance is '${minimumBalance}'."
            )
            return
        } else {
            account.withdraw(amount)
            withdrawalLimiter.recordWithdrawal(amount)
            outputter.output("your new balance is: '${account.balance}'");
        }
    }

    override val key: String = "withdraw"
}