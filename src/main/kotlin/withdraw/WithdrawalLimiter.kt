package withdraw

import amounts.MaximumWithdrawal
import command.PerSession
import javax.inject.Inject

@PerSession
class WithdrawalLimiter @Inject constructor(
    @MaximumWithdrawal var remainingWithdrawalLimit: Double
) {
    fun recordDeposit(amount: Double) {
        remainingWithdrawalLimit += amount
    }

    fun recordWithdrawal(amount: Double) {
        remainingWithdrawalLimit -= amount
    }
}