package com.example.yatask.data.repository

import com.example.yatask.data.models.TodoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface TodoItemsRepository {

    fun getAllNoteList() : StateFlow<List<TodoItem>>

    fun addNote(note : TodoItem)

    fun removeNote(note: TodoItem)

    fun editNote(note: TodoItem)

    suspend fun findNote(id : String) : TodoItem?

    fun complectedTaskSize() : Flow<Int>
}