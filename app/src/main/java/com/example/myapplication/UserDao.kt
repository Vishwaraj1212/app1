package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
//    @Query("SELECT * FROM user")
//    fun getAll(): List<User>
//
//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): List<User>
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): User

    @Query("SELECT * FROM usertable")
    fun getdata(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertdata(user: User)



    //@Delete
    //fun delete(user: User)
}