package com.example.yatask.domain.useCase

import com.example.yatask.data.source.remote.models.TodoRequestDTO
import com.example.yatask.domain.models.TodoDomainDTO

interface AddTodoUseCase {

    suspend fun addNote(note : TodoDomainDTO) : Result<Boolean>

}