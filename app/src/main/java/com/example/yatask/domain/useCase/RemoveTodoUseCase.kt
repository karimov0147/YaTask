package com.example.yatask.domain.useCase

interface RemoveTodoUseCase {

    suspend fun removeNote(id: String) : Result<Boolean>

}