package com.aidoantonioc.expantapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,

    // EXPENSE or INCOME
    val type: String,

    val icon: String = "category"
)