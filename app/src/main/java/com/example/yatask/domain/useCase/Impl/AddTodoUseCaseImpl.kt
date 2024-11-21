package com.example.yatask.domain.useCase.Impl

import android.util.Log
import com.example.yatask.data.source.remote.models.TodoBaseRequest
import com.example.yatask.data.source.remote.models.TodoRequestDTO
import com.example.yatask.domain.models.TodoDomainDTO
import com.example.yatask.domain.repository.TodoItemsRepository
import com.example.yatask.domain.useCase.AddTodoUseCase
import com.example.yatask.utils.Revision
import com.example.yatask.utils.toRequestDTO
import java.util.concurrent.CancellationException
import javax.inject.Inject

class AddTodoUseCaseImpl @Inject constructor(
    private val todoItemsRepository: TodoItemsRepository
) : AddTodoUseCase {
    override suspend fun addNote(note: TodoDomainDTO): Result<Boolean> {
        return try {
            val data = todoItemsRepository.addNote(note.toRequestDTO())
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
