package com.example.yatask.domain.useCase.Impl

import android.util.Log
import com.example.yatask.data.source.remote.models.TodoBaseRequest
import com.example.yatask.domain.models.TodoDomainDTO
import com.example.yatask.domain.repository.TodoItemsRepository
import com.example.yatask.domain.useCase.EditTodoUseCase
import com.example.yatask.utils.Revision
import com.example.yatask.utils.toRequestDTO
import java.util.concurrent.CancellationException
import javax.inject.Inject

class EditTodoUseCaseImpl @Inject constructor(
    private val todoItemsRepository: TodoItemsRepository)
    : EditTodoUseCase {

    override suspend fun editNote(note: TodoDomainDTO): Result<Boolean> {
        return try {
            val data = todoItemsRepository.editNote(note.toRequestDTO())
            if (data.isSuccessful){
                Result.success(true).also {
                    Revision.revison = data.body()?.revision ?: 0
                }
            }else{
                throw Exception("Network request unsuccessful: ${data.code()}, ${data.message()}")
            }
        } catch (e : CancellationException){
            Log.d("CancellationException", "getAllNoteList: ${e.message} ")
            throw e
        } catch (e : Exception){
            Result.failure(e)
        }
    }


}