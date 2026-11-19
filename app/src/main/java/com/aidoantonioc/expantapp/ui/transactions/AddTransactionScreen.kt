
package com.aidoantonioc.expantapp.ui.transactions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aidoantonioc.expantapp.ui.categories.CategorySelection

@Composable
fun AddTransactionScreen() {

    // Form input states
    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var transactionType by remember {
        mutableStateOf("EXPENSE")
    }
    var selectedCategory by remember {
        mutableStateOf("")
    }

    val expenseCategories = listOf(
        "Food",
        "Transportation",
        "Shopping",
        "Entertainment",
        "Bills",
        "Health"
    )

    val incomeCategories = listOf(
        "Salary",
        "Freelance",
        "Business",
        "Investment",
        "Other Income"
    )

    val availableCategories = if (transactionType == "EXPENSE") {
        expenseCategories
    } else {
        incomeCategories
    }

    // Form feedback states
    var errorMessage by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf("") }

    Scaffold { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {

            Text(
                text = "Add Transaction",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Enter your income or expense details",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Transaction title
            Text(
                text = "Transaction Title",
                fontWeight = FontWeight.SemiBold
            )

            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    errorMessage = ""
                    successMessage = ""
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Example: Lunch, Salary")
                },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Transaction amount
            Text(
                text = "Amount (Rp)",
                fontWeight = FontWeight.SemiBold
            )

            OutlinedTextField(
                value = amount,
                onValueChange = {
                    amount = it.filter { character ->
                        character.isDigit()
                    }
                    errorMessage = ""
                    successMessage = ""
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Example: 50000")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Transaction type
            Text(
                text = "Transaction Type",
                fontWeight = FontWeight.SemiBold
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Row {
                    RadioButton(
                        selected = transactionType == "EXPENSE",
                        onClick = {
                            transactionType = "EXPENSE"
                            selectedCategory = ""
                            successMessage = ""
                        }
                    )

                    Text(
                        text = "Expense",
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }

                Row {
                    RadioButton(
                        selected = transactionType == "INCOME",
                        onClick = {
                            transactionType = "INCOME"
                            selectedCategory = ""
                            successMessage = ""
                        }
                    )

                    Text(
                        text = "Income",
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Category",
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            CategorySelection(
                categories = availableCategories,
                selectedCategory = selectedCategory,
                onCategorySelected = {
                    selectedCategory = it
                    errorMessage = ""
                    successMessage = ""
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Optional note
            Text(
                text = "Note (Optional)",
                fontWeight = FontWeight.SemiBold
            )

            OutlinedTextField(
                value = note,
                onValueChange = {
                    note = it
                    successMessage = ""
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Add additional information")
                },
                minLines = 3,
                maxLines = 4
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Save button
            Button(
                onClick = {

                    val amountValue = amount.toLongOrNull()

                    when {
                        title.isBlank() -> {
                            errorMessage =
                                "Please enter a transaction title."
                            successMessage = ""
                        }

                        selectedCategory.isBlank() -> {
                            errorMessage = "Please select a category."
                            successMessage = "Transaction details are valid!"
                        }

                        amountValue == null || amountValue <= 0 -> {
                            errorMessage =
                                "Please enter a valid amount."
                            successMessage = ""
                        }

                        else -> {
                            errorMessage = ""
                            successMessage =
                                "Transaction details are valid!"
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text(
                    text = "Save Transaction",
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Feedback message
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }

            if (successMessage.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE8F5E9)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = successMessage,
                            color = Color(0xFF15803D),
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Type: $transactionType\n" +
                                    "Title: $title\n" +
                                    "Amount: Rp $amount\n" +
                                    "Note: ${note.ifBlank { "None" }}",
                            color = Color.DarkGray,
                            modifier = Modifier.padding(top = 8.dp)
                        )

                        Text(
                            text = "This is a temporary preview. " +
                                    "The transaction has not been saved to the database.",
                            color = Color.Gray,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}
