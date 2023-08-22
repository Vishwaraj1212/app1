package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usertable")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    var name: String,

    @ColumnInfo(name = "city")
    var city: String

)
