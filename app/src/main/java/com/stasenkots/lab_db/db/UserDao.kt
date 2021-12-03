package com.stasenkots.lab_db.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table WHERE  name LIKE :filter || '%' OR surname LIKE :filter || '%'")
    fun getUsers(filter: String): Flow<List<User>>

    @Query("SELECT COUNT(*) FROM user_table")
    fun getAllUsersCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

}