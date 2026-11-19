
package com.aidoantonioc.expantapp.ui.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val ScreenBackground = Color(0xFFF5F7FB)
private val IncomeGreen = Color(0xFF16A34A)
private val ExpenseRed = Color(0xFFDC2626)
private val PrimaryBlue = Color(0xFF2563EB)

data class TransactionItemData(
    val title: String,
    val category: String,
    val date: String,
    val amount: Long,
    val type: String
)

@Composable
fun TransactionListScreen() {

    // Temporary sample transactions
    val transactions = listOf(
        TransactionItemData(
            title = "Lunch",
            category = "Food",
            date = "19 November 2026",
            amount = 35000,
            type = "EXPENSE"
        ),
        TransactionItemData(
            title = "Monthly Salary",
            category = "Salary",
            date = "18 November 2026",
            amount = 4000000,
            type = "INCOME"
        ),
        TransactionItemData(
            title = "Transportation",
            category = "Transportation",
            date = "17 November 2026",
            amount = 15000,
            type = "EXPENSE"
        ),
        TransactionItemData(
            title = "Movie Ticket",
            category = "Entertainment",
            date = "16 November 2026",
            amount = 50000,
            type = "EXPENSE"
        ),
        TransactionItemData(
            title = "Freelance Project",
            category = "Freelance",
            date = "15 November 2026",
            amount = 750000,
            type = "INCOME"
        )
    )

    Scaffold(
        containerColor = ScreenBackground
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "My Transactions",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "View your income and expenses",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Monthly summary
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = PrimaryBlue
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "November 2026",
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Total Transactions: ${transactions.size}",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "All Transactions",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(transactions) { transaction ->
                    TransactionRow(transaction)
                }
            }
        }
    }
}

@Composable
fun TransactionRow(transaction: TransactionItemData) {

    val isIncome = transaction.type == "INCOME"

    val amountColor = if (isIncome) {
        IncomeGreen
    } else {
        ExpenseRed
    }

    val amountPrefix = if (isIncome) "+" else "-"

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(14.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = transaction.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = transaction.category,
                    fontSize = 13.sp,
                    color = Color.Gray
                )

                Text(
                    text = transaction.date,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Text(
                text = "$amountPrefix Rp ${"%,d".format(transaction.amount)}",
                color = amountColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
