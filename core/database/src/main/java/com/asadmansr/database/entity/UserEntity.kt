package com.asadmansr.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/** UserEntity - Used as a model to store user data to the database
 * @param id: String - unique identifier for the user (@todo)
 * @param name: String - full name of the user (Jennie Nichols)
 * @param dob: String - date of birth of the user (1992-03-08T15:13:16.688Z)
 * @param gender: String - gender of the user (female)
 * @param address: String - full address of the user (8929 Valwood Pkwy, Billings, Michigan, United States)
 * @param picture: String - url of the user's picture (https://randomuser.me/api/portraits/women/75.jpg)
 */
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
