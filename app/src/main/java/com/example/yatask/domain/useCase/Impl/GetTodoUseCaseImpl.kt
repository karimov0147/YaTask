package com.example.yatask.domain.useCase.Impl

import android.util.Log
import com.example.yatask.data.source.remote.models.TodoResponseDTO
import com.example.yatask.domain.models.TodoDomainDTO
import com.example.yatask.domain.repository.TodoItemsRepository
import com.example.yatask.domain.useCase.GetTodoUseCase
import com.example.yatask.utils.Revision
import com.example.yatask.utils.toDomainDTO
import java.util.concurrent.CancellationException
import javax.inject.Inject

class GetTodoUseCaseImpl @Inject constructor(
    private val todoItemsRepository: TodoItemsRepository
) : GetTodoUseCase {
    override suspend fun getAllNoteList(): Result<List<TodoDomainDTO>> {
        return try {
            val data = todoItemsRepository.getAllNoteList()
            if (data.isSuccessful){
                Result.success(data.body()?.data?.map { it.toDomainDTO() } ?: listOf()).also {
                    Revision.revison = data.body()?.revision ?: 0
                }
            } else {
                throw Exception("Network request unsuccessful: ${data.code()}, ${data.message()}")
            }
        }catch (e : CancellationException){
            Log.d("CancellationException", "getAllNoteList: ${e.message} ")
            throw e
        }catch (e : Exception){
            Result.failure(e)
        }
    }

    override suspend fun getNoteById(id: String): Result<TodoDomainDTO?> {
        return try {
            val data = todoItemsRepository.getNoteById(id)
            if (data.isSuccessful){
                Result.success(data.body()?.data?.toDomainDTO()).also {
                    Revision.revison = data.body()?.revision ?: 0
                }
            } else {
                throw Exception("Network request unsuccessful: ${data.code()}, ${data.message()}")
            }
        }catch (e : CancellationException){
            Log.d("CancellationException", "getAllNoteList: ${e.message} ")
            throw e
        }catch (e : Exception){
            Result.failure(e)
        }
    }


}