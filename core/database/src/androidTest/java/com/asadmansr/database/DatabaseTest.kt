package com.asadmansr.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.asadmansr.database.dao.UserDao
import org.junit.After
import org.junit.Before

internal abstract class DatabaseTest {

    private lateinit var database: AppDatabase
    protected lateinit var userDao: UserDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()

        userDao = database.userDao()
    }

    @After
    fun teardown() {
        database.close()
    }
}
