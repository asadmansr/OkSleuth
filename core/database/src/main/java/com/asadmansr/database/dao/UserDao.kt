package com.asadmansr.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.asadmansr.database.entity.UserEntity

@Dao
interface UserDao {

    // Insert new user regardless if the user already exist
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserEntity)

    // Update the user using the entity
    @Update
    suspend fun updateUser(user: UserEntity): Int

    // Get a single user with the specified id
    @Query("SELECT * from users WHERE id = :id")
    suspend fun getUserById(id: String): UserEntity

    // Get all users
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserEntity>

    // Delete a single user with teh specified id
    @Query("DELETE FROM users WHERE id = :id")
    suspend fun deleteUserById(id: String): Int

    // Delete all users
    @Query("DELETE FROM users")
    suspend fun deleteAllUsers(): Int
}
