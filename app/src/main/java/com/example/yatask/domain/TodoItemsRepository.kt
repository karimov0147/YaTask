package com.example.yatask.domain

import com.example.yatask.ui.models.TodoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface TodoItemsRepository {

    fun getAllNoteList() : StateFlow<List<TodoItem>>

    fun addNote(note : TodoItem)

    fun removeNote(note: TodoItem)

    fun editNote(note: TodoItem)

    suspend fun findNote(id : String) : TodoItem?

    fun saveNote(note: TodoItem)

    fun complectedTaskSize() : Flow<Int>
}