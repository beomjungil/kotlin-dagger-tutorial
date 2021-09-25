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
    override val key: String = "예금"
    override fun handleAmount(amount: Double) {
        account.deposit(amount)
        withdrawalLimiter.recordDeposit(amount)
        outputter.output("${account.username}님의 잔액: ${account.balance}");
    }
}