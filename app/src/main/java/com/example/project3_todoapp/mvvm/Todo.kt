package com.example.project3_todoapp.mvvm

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Todo")
data class Todo (
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    @ColumnInfo(name ="Title")
    val title:String,
    @ColumnInfo(name = "Description")
    val description: String,
    @ColumnInfo(name = "Time")
    val time: String
) {
}