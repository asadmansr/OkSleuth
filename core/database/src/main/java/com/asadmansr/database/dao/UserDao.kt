package com.asadmansr.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.asadmansr.database.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity): Int

    @Query("SELECT * from users WHERE id = :id")
    suspend fun getUserById(id: String): UserEntity

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserEntity>

    @Query("DELETE FROM users WHERE id = :id")
    suspend fun deleteUserById(id: String): Int

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers(): Int
}
