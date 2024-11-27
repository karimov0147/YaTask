package com.example.yatask.domain.useCase.Impl

import android.util.Log
import com.example.yatask.domain.repository.TodoItemsRepository
import com.example.yatask.domain.useCase.RemoveTodoUseCase
import com.example.yatask.utils.Revision
import java.util.concurrent.CancellationException
import javax.inject.Inject

class RemoveTodoUseCaseImpl @Inject constructor(
    private val todoItemsRepository: TodoItemsRepository
) : RemoveTodoUseCase {
    override suspend fun removeNote(id: String): Result<Boolean> {
        return try {
            val data = todoItemsRepository.removeNote(id)
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