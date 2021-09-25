package command

import dagger.Binds
import dagger.BindsOptionalOf
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import database.Database
import helloworld.HelloWorldCommand
import login.LoginCommand

@Module
interface CommandsModule {
    @Binds
    @IntoMap
    @StringKey("hello")
    fun helloWorld(command: HelloWorldCommand): Command

    @Binds
    @IntoMap
    @StringKey("login")
    fun login(command: LoginCommand): Command

    @BindsOptionalOf
    fun loggedInAccount(): Database.Account
}