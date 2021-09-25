package user

import dagger.Module
import dagger.Provides
import database.Database
import database.Database.Account

@Module
interface AccountModule {
    companion object {
        @Provides
        fun account(database: Database, @Username username: String): Account {
            return database.getAccount(username)
        }
    }
}