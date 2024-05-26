package com.example.project3_todoapp.MVVM

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

// đối tượng truy nhập dữ liệu
// data access object

@Dao
interface TodoDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("SELECT * FROM TODO WHERE id = :id")
    fun getToDo(id: Int): Flow<Todo>

    @Query("SELECT * FROM TODO ORDER BY Time DESC ")
    fun getAllToDos() : Flow<List<Todo>>
}