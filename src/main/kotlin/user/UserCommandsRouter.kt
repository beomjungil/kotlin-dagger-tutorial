package user

import command.CommandRouter
import command.PerSession
import dagger.BindsInstance
import dagger.Module
import dagger.Subcomponent
import database.UserCommandsModule

@JvmSuppressWildcards
@PerSession
@Subcomponent(modules = [AccountModule::class, UserCommandsModule::class])
interface UserCommandsRouter {
    fun router(): CommandRouter

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance @Username username: String
        ): UserCommandsRouter
    }

    @Module(subcomponents = [UserCommandsRouter::class])
    interface InstallationModule {}
}