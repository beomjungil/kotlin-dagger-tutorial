package database

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Database @Inject constructor() {
    class Account(
        val username: String,
        var balance: Double = 0.0
    ) {
        fun deposit(amount: Double) {
            balance += amount
        }

        fun withdraw(amount: Double) {
            balance -= amount
        }
    }

    fun getAccount(username: String): Account {
        return accounts.getOrPut(username) {
            Account(username)
        }
    }

    private val accounts: MutableMap<String, Account> = mutableMapOf()
}