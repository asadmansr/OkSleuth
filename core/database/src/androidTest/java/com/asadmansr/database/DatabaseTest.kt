package com.asadmansr.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.asadmansr.database.dao.UserDao
import org.junit.After
import org.junit.Before

/**
 * Android Developer Docs provide a way to test and debug database. They recommend writing a JUnit
 * test that runs on Android device. Since the tests will not require an activity, they should be
 * faster to execute than UI tests. We just have to keep an eye on emulator boot-up speeds.
 *
 * DatabaseTest creates an instance of the in-memory version of the database that we can use to
 * perform database operations. It also supports testing migrations, which we will need to do later.
 *
 * "Information stored in an in memory database disappears when the process is killed. Once a
 * database is built, you should keep a reference to it and re-use it." Useful information on when
 * a database instance should be kept or recycled to provide clean tests.
 */
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
