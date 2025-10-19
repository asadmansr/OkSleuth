package com.asadmansr.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val dob: String,
    val gender: String,
    val address: String,
    val picture: String,
)
