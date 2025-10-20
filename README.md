# OkSleuth

OkSleuth is a fictional detective agency app where users can explore cases, log clues, and track solved mysteries.

This is a sample playground app to explore Android testing techniques while developing a structured app architecture.

## Components

The application has the following components that are tested through unit or UI tests.

### Database

:core:database represents the database module of the application
- UserEntity and UserDao are specification for a user object that get stored in Room database.
- UserDaoTest in androidTest tests the following entity and dao operations in an in-memory version of database on an Android device.
