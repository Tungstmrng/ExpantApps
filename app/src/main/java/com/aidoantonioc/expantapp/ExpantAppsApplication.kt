
package com.aidoantonioc.expantapp

import android.app.Application
import com.aidoantonioc.expantapp.data.local.ExpenseDatabase
import com.aidoantonioc.expantapp.data.local.entity.CategoryEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import android.util.Log
import com.aidoantonioc.expantapp.data.local.entity.ExpenseEntity

class ExpantAppsApplication : Application() {

    private val applicationScope =
        CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()

        val database = ExpenseDatabase.getDatabase(this)

        applicationScope.launch {

            val categoryDao = database.categoryDao()

            if (categoryDao.getCategoryCount() == 0) {

                val defaultCategories = listOf(

                    // Expense categories
                    CategoryEntity(name = "Food", type = "EXPENSE", icon = "food"),
                    CategoryEntity(name = "Transportation", type = "EXPENSE", icon = "transport"),
                    CategoryEntity(name = "Shopping", type = "EXPENSE", icon = "shopping"),
                    CategoryEntity(name = "Entertainment", type = "EXPENSE", icon = "entertainment"),
                    CategoryEntity(name = "Bills", type = "EXPENSE", icon = "bills"),
                    CategoryEntity(name = "Health", type = "EXPENSE", icon = "health"),

                    // Income categories
                    CategoryEntity(name = "Salary", type = "INCOME", icon = "salary"),
                    CategoryEntity(name = "Freelance", type = "INCOME", icon = "freelance"),
                    CategoryEntity(name = "Business", type = "INCOME", icon = "business"),
                    CategoryEntity(name = "Investment", type = "INCOME", icon = "investment"),
                    CategoryEntity(name = "Other Income", type = "INCOME", icon = "income")
                )

                categoryDao.insertCategories(defaultCategories)
            }
            // Temporary Room database test
            val expenseDao = database.expenseDao()

            val testExpense = ExpenseEntity(
                title = "Database Test",
                amount = 25000L,
                categoryId = null,
                type = "EXPENSE",
                date = System.currentTimeMillis(),
                note = "Testing Room database"
            )

        }
    }
}
