package deposit

import command.DoubleCommand
import database.Database
import outputter.Outputter
import withdraw.WithdrawalLimiter
import javax.inject.Inject

class DepositCommand @Inject constructor(
    private val account: Database.Account,
    private val outputter: Outputter,
    private val withdrawalLimiter: WithdrawalLimiter
): DoubleCommand(outputter) {
    override val key: String = "deposit"
    override fun handleAmount(amount: Double) {
        account.deposit(amount)
        withdrawalLimiter.recordDeposit(amount)
        outputter.output("${account.username}'s new balance is: ${account.balance}");
    }
}