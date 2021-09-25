package database

import command.Command
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import deposit.DepositCommand
import logout.LogoutCommand
import withdraw.WithdrawCommand

@Module
abstract class UserCommandsModule {
    @Binds
    @IntoMap
    @StringKey("예금")
    abstract fun depositCommand(command: DepositCommand?): Command?

    @Binds
    @IntoMap
    @StringKey("출금")
    abstract fun withdrawCommand(command: WithdrawCommand?): Command?

    @Binds
    @IntoMap
    @StringKey("로그아웃")
    abstract fun logoutCommand(command: LogoutCommand?): Command?
}