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
            outputter.output("'$amount' 를 출금할 수 없습니다. 최대 출금 한도는 '$remainingWithdrawalLimit' 입니다.")
            return
        }

        val newBalance = account.balance - amount

        if ((newBalance - minimumBalance) < 0) {
            outputter.output(
                "'$amount' 출금 시 잔액이 부족합니다. 현재 잔액은 '${account.balance}'며, 최소 잔액은 '${minimumBalance}' 입니다."
            )
            return
        } else {
            account.withdraw(amount)
            withdrawalLimiter.recordWithdrawal(amount)
            outputter.output("출금이 완료되었습니다. 현재 잔액은 '${account.balance}' 입니다.");
        }
    }

    override val key: String = "출금"
}