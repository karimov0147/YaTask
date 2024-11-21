package com.example.yatask.data.repository

import com.example.yatask.domain.repository.TodoItemsRepository
import com.example.yatask.data.source.remote.TodoApiService
import com.example.yatask.data.source.remote.models.TodoBaseRequest
import com.example.yatask.data.source.remote.models.TodoBaseResponse
import com.example.yatask.data.source.remote.models.TodoRequestDTO
import com.example.yatask.data.source.remote.models.TodoResponseDTO
import retrofit2.Response
import javax.inject.Inject


class TodoItemRepositoryImpl @Inject constructor(
    private val api: TodoApiService
) : TodoItemsRepository {

    override suspend fun getAllNoteList() : Response<TodoBaseResponse<List<TodoResponseDTO>>> = api.getAllNoteList()

    override suspend fun addNote(note: TodoRequestDTO): Response<TodoBaseResponse<Any>> = api.addNote(TodoBaseRequest(note))

    override suspend fun removeNote(id: String): Response<TodoBaseResponse<Any>>  = api.removeNote(id)

    override suspend fun editNote(note: TodoRequestDTO): Response<TodoBaseResponse<Any>> = api.editNote(note.id , TodoBaseRequest(note))

    override suspend fun getNoteById(id : String): Response<TodoBaseResponse<TodoResponseDTO>>  = api.getNoteById(id)

}