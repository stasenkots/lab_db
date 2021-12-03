package com.stasenkots.lab_db.db

import androidx.room.Entity

@Entity(tableName = "user_table", primaryKeys = ["name", "surname"])
data class User(
    val name: String,
    val surname: String,
    val comment: String
)