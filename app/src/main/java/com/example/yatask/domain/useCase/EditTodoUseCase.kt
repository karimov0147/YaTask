package com.example.yatask.domain.useCase

import com.example.yatask.data.source.remote.models.TodoRequestDTO
import com.example.yatask.domain.models.TodoDomainDTO

interface EditTodoUseCase {

    suspend fun editNote(note: TodoDomainDTO) : Result<Boolean>

}