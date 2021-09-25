package amounts

import dagger.Module
import dagger.Provides

@Module
interface AmountsModule {
    companion object {
        @Provides
        @MinimumBalance
        fun minimumBalance(): Double {
            return 0.0
        }

        @Provides
        @MaximumWithdrawal
        fun maximumWithdrawal(): Double {
            return 1000.0
        }
    }
}