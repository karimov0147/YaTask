package com.example.yatask.domain.repository

import com.example.yatask.data.source.remote.models.TodoBaseResponse
import com.example.yatask.data.source.remote.models.TodoRequestDTO
import com.example.yatask.data.source.remote.models.TodoResponseDTO
import retrofit2.Response

interface TodoItemsRepository {

    suspend fun getAllNoteList() : Response<TodoBaseResponse<List<TodoResponseDTO>>>

    suspend fun addNote(note : TodoRequestDTO) :  Response<TodoBaseResponse<Any>>

    suspend fun removeNote(id: String) : Response<TodoBaseResponse<Any>>

    suspend fun editNote(note: TodoRequestDTO) :  Response<TodoBaseResponse<Any>>

    suspend fun getNoteById(id : String) : Response<TodoBaseResponse<TodoResponseDTO>>
}