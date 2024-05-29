package com.example.project3_todoapp

import android.app.Application
import com.example.project3_todoapp.database.TodoDatabase

//Cách này sẽ tạo cơ sở dữ liệu (cơ sở dữ liệu thực trên đĩa) trong lần truy cập đầu tiên.

class InventoryApplication: Application() {
    val database: TodoDatabase by lazy { TodoDatabase.invoke(this) }
}