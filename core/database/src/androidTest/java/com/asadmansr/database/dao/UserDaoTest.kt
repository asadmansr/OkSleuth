package com.asadmansr.database.dao

import com.asadmansr.database.DatabaseTest
import com.asadmansr.database.entity.UserEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test


internal class UserDaoTest : DatabaseTest() {

    @Test
    fun insertUser_singleUser_returnsOneUser() = runTest {
        val user = UserEntity(
            id = "userId",
            name = "Jennie Nichols",
            dob = "1992-03-08T15:13:16.688Z",
            gender = "female",
            address = "8929 Valwood Pkwy, Billings, Michigan, United States",
            picture = "https://randomuser.me/api/portraits/women/75.jpg",
        )

        userDao.insertUser(user)

        val savedUsers = userDao.getAllUsers()

        assertEquals(1, savedUsers.size)
        assertEquals("userId", savedUsers.first().id)
    }

    @Test
    fun insertUser_multipleUsers_returnsMultipleUsers() = runTest {
        val firstUser = UserEntity(
            id = "first-userId",
            name = "Jennie Nichols",
            dob = "1992-03-08T15:13:16.688Z",
            gender = "female",
            address = "8929 Valwood Pkwy, Billings, Michigan, United States",
            picture = "https://randomuser.me/api/portraits/women/75.jpg",
        )
        val secondUser = UserEntity(
            id = "second-userId",
            name = "James Smith",
            dob = "1995-03-08T15:13:16.688Z",
            gender = "male",
            address = "1022 Valwood Pkwy, Billings, Michigan, United States",
            picture = "https://randomuser.me/api/portraits/men/75.jpg",
        )

        userDao.insertUser(firstUser)
        userDao.insertUser(secondUser)

        val savedUsers = userDao.getAllUsers()

        assertEquals(2, savedUsers.size)
    }

    @Test
    fun insertUser_conflictUser_ignoresConflictResolution() = runTest {
        val firstUser = UserEntity(
            id = "userId",
            name = "Jennie Nichols",
            dob = "1992-03-08T15:13:16.688Z",
            gender = "female",
            address = "8929 Valwood Pkwy, Billings, Michigan, United States",
            picture = "https://randomuser.me/api/portraits/women/75.jpg",
        )
        val secondUser = UserEntity(
            id = "userId",
            name = "James Smith",
            dob = "1995-03-08T15:13:16.688Z",
            gender = "male",
            address = "1022 Valwood Pkwy, Billings, Michigan, United States",
            picture = "https://randomuser.me/api/portraits/men/75.jpg",
        )

        userDao.insertUser(firstUser)
        userDao.insertUser(secondUser)

        val savedUsers = userDao.getAllUsers()

        assertEquals(1, savedUsers.size)
        assertEquals("userId", savedUsers.first().id)
    }

    @Test
    fun updateUser_singleUser_returnsUpdatedUserData() = runTest {
        val firstUser = UserEntity(
            id = "userId",
            name = "Jennie Nichols",
            dob = "1992-03-08T15:13:16.688Z",
            gender = "female",
            address = "8929 Valwood Pkwy, Billings, Michigan, United States",
            picture = "https://randomuser.me/api/portraits/women/75.jpg",
        )
        val updatedUser = UserEntity(
            id = "userId",
            name = "Jennie Nichols",
            dob = "1992-03-08T15:13:16.688Z",
            gender = "female",
            address = "8929 Valwood Pkwy, Billings, Michigan, United States",
            picture = "https://randomuser.me/api/portraits/women/76.jpg",
        )

        userDao.insertUser(firstUser)
        var savedUser = userDao.getUserById("userId")
        assertEquals("https://randomuser.me/api/portraits/women/75.jpg", savedUser.picture)

        userDao.updateUser(updatedUser)
        savedUser = userDao.getUserById("userId")
        assertEquals("https://randomuser.me/api/portraits/women/76.jpg", savedUser.picture)
    }

    @Test
    fun deleteAllUsers_multipleUsers_returnsEmptyDatabase() = runTest {
        val firstUser = UserEntity(
            id = "first-userId",
            name = "Jennie Nichols",
            dob = "1992-03-08T15:13:16.688Z",
            gender = "female",
            address = "8929 Valwood Pkwy, Billings, Michigan, United States",
            picture = "https://randomuser.me/api/portraits/women/75.jpg",
        )
        val secondUser = UserEntity(
            id = "second-userId",
            name = "James Smith",
            dob = "1995-03-08T15:13:16.688Z",
            gender = "male",
            address = "1022 Valwood Pkwy, Billings, Michigan, United States",
            picture = "https://randomuser.me/api/portraits/men/75.jpg",
        )

        userDao.insertUser(firstUser)
        userDao.insertUser(secondUser)

        var savedUsers = userDao.getAllUsers()
        assertEquals(2, savedUsers.size)

        userDao.deleteAllUsers()
        savedUsers = userDao.getAllUsers()
        assertEquals(0, savedUsers.size)
    }

    @Test
    fun deleteUserById_singleUser_returnsUsersWithoutDeletedUser() = runTest {
        val firstUser = UserEntity(
            id = "first-userId",
            name = "Jennie Nichols",
            dob = "1992-03-08T15:13:16.688Z",
            gender = "female",
            address = "8929 Valwood Pkwy, Billings, Michigan, United States",
            picture = "https://randomuser.me/api/portraits/women/75.jpg",
        )
        val secondUser = UserEntity(
            id = "second-userId",
            name = "James Smith",
            dob = "1995-03-08T15:13:16.688Z",
            gender = "male",
            address = "1022 Valwood Pkwy, Billings, Michigan, United States",
            picture = "https://randomuser.me/api/portraits/men/75.jpg",
        )

        userDao.insertUser(firstUser)
        userDao.insertUser(secondUser)

        var savedUsers = userDao.getAllUsers()
        assertEquals(2, savedUsers.size)

        userDao.deleteUserById("first-userId")
        savedUsers = userDao.getAllUsers()
        assertEquals(1, savedUsers.size)
        assertEquals("second-userId", savedUsers.first().id)
    }
}
