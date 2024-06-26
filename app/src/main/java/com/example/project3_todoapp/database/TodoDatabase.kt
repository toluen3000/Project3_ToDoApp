package com.example.project3_todoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.project3_todoapp.mvvm.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)

 abstract class TodoDatabase:RoomDatabase() {

 //Cơ sở dữ liệu cần biết về DAO.
 // Bên trong phần nội dung của lớp, hãy khai báo hàm trừu tượng trả về ItemDao. Bạn có thể có nhiều DAO.
     abstract fun todoDao(): TodoDAO

 //Bên dưới hàm trừu tượng này, hãy xác định một đối tượng companion.
 // Đối tượng đồng hành này cho phép truy cập vào các phương thức tạo hoặc lấy cơ sở dữ liệu bằng cách dùng tên lớp làm bộ hạn định.
     companion object{
         @Volatile
         private var INSTANCE: TodoDatabase? = null
        private val LOCK = Any()
     //Bên dưới INSTANCE, vẫn bên trong đối tượng companion, hãy khai báo một phương thức getDatabase()
     // có tham số Context mà hàm tạo cơ sở dữ liệu sẽ cần đến.
     //Trả về một loại ItemRoomDatabase. Bạn sẽ thấy lỗi vì getDatabase() chưa trả về giá trị nào.

//        fun getDatabase(context: Context): TodoDatabase {
//            return INSTANCE ?: synchronized(this){
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    TodoDatabase::class.java,
//                    "todo_database"
//                ).fallbackToDestructiveMigration().build()
//    //Chỉ định INSTANCE = instance trong bên trong khối synchronized.
//                INSTANCE = instance
//
//                return  instance
//            }
//        }

     operator  fun invoke(context: Context) = INSTANCE?: synchronized(LOCK){
         INSTANCE?: createDatabase(context).also {
             INSTANCE = it
         }
     }

     private fun createDatabase(context: Context) = Room.databaseBuilder(
         context.applicationContext,
         TodoDatabase::class.java,
         "todo_database"
     ).build()

     }
}