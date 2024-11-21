package com.example.yatask.data.source.remote

import com.example.yatask.data.source.remote.models.TodoBaseRequest
import com.example.yatask.data.source.remote.models.TodoBaseResponse
import com.example.yatask.data.source.remote.models.TodoRequestDTO
import com.example.yatask.data.source.remote.models.TodoResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TodoApiService {

    @GET("todo/list")
    suspend fun getAllNoteList() : Response<TodoBaseResponse<List<TodoResponseDTO>>>

    @GET("todo/list/{id}")
    suspend fun getNoteById(@Path("id") id : String) : Response<TodoBaseResponse<TodoResponseDTO>>

    @POST("todo/list")
    suspend fun addNote(
        @Body task: TodoBaseRequest<TodoRequestDTO>
    ): Response<TodoBaseResponse<Any>>

    @DELETE("todo/list/{id}")
    suspend fun removeNote(
        @Path("id") id : String
    ) : Response<TodoBaseResponse<Any>>


    @PUT("todo/list/{id}")
    suspend fun editNote(
        @Path("id") id : String,
        @Body task: TodoBaseRequest<TodoRequestDTO>
    ) : Response<TodoBaseResponse<Any>>


}