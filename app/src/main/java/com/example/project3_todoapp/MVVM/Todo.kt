package com.example.project3_todoapp.MVVM

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "Todo")
data class Todo (
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    @ColumnInfo(name ="Title")
    val title:String,
    @ColumnInfo(name = "Description")
    val description: String,
    @ColumnInfo(name = "Time")
    val time: Date
) {
}