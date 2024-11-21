package com.example.yatask.domain.useCase

import com.example.yatask.domain.models.TodoDomainDTO

interface GetTodoUseCase {

    suspend fun getAllNoteList() : Result<List<TodoDomainDTO>>

    suspend fun getNoteById(id : String) : Result<TodoDomainDTO?>
}