package command

import amounts.AmountsModule
import dagger.Component
import outputter.SystemOutModule
import user.UserCommandsRouter
import javax.inject.Singleton

@Singleton
@Component(modules = [
    CommandsModule::class,
    UserCommandsRouter.InstallationModule::class,
    AmountsModule::class,
    SystemOutModule::class,
])
interface CommandProcessorFactory {
    fun processor(): CommandProcessor
}
