package com.example.yatask.data.repository

import android.util.Log
import com.example.yatask.ui.models.TodoItem
import com.example.yatask.domain.TodoItemsRepository
import com.example.yatask.data.source.remote.TodoApiService
import com.example.yatask.utils.Importance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import java.util.Date
import javax.inject.Inject


class TodoItemRepositoryImpl @Inject constructor(
    private val api: TodoApiService
) : TodoItemsRepository {
    private val taskList = mutableListOf(
        TodoItem("1" , "Купить что-то" , Importance.NORMAL , Date() , true , Date() , Date() ),
        TodoItem("2" , "Купить что-то, где-то, зачем-то, но зачем не очень понятно" , Importance.HIGH , Date() , false , Date() , Date() ) ,
        TodoItem("3" , "Купить что-то, где-то, зачем-то, но зачем не очень понятно, но точно чтобы показать как обр…" , Importance.LOW , Date() , false , Date() , Date() ) ,
        TodoItem("4" , "Купить что-то" , Importance.HIGH , Date() , false , Date() , Date() ),
        TodoItem("5" , "Купить что-то, где-то, зачем-то, но зачем не очень понятно" , Importance.NORMAL , Date() , true , Date() , Date() ) ,
        TodoItem("6" , "Купить что-то, где-то, зачем-то, но зачем не очень понятно, но точно чтобы показать как обр…" , Importance.LOW , Date() , false , Date() , Date() ) ,
        )
    private var _task: MutableStateFlow<List<TodoItem>> = MutableStateFlow(taskList)
    private val task : StateFlow<List<TodoItem>> = _task

    override fun getAllNoteList(): StateFlow<List<TodoItem>> {
        return task
    }

    override  fun addNote(note: TodoItem) {
        val arr = mutableListOf<TodoItem>()
        arr.addAll(_task.value)
        arr.add(note)
        _task.update { arr }
    }

    override  fun removeNote(note: TodoItem) {
        val arr = mutableListOf<TodoItem>()
        arr.clear()
        arr.addAll(_task.value)
        arr.remove(note)
        _task.update { arr }
    }

    override  fun editNote(note: TodoItem) {
        val arr = mutableListOf<TodoItem>()
        arr.clear()
        arr.addAll(_task.value)
        for (it in arr.indices){
            if (arr[it].id == note.id){
                arr[it] = note
            }
        }
        _task.update { arr }
    }

    override suspend fun findNote(id: String) : TodoItem? {
        val arr = mutableListOf<TodoItem>()
        arr.addAll(_task.value)
        for (it in arr.indices){
            if (arr[it].id == id){
                return arr[it]
            }
        }
        return null
    }

    override fun saveNote(note: TodoItem) {
        val arr = mutableListOf<TodoItem>()
        arr.addAll(_task.value)
        Log.d("yaTaskLog", "saveNote:$note ")
        for (it in arr.indices){
            Log.d("yaTaskLog", "for:$it ")
            if (arr[it].id == note.id){
                Log.d("yaTaskLog", "if:$note   ${arr[it]} ")
                arr[it] = note
                _task.update { arr }.also {
                    Log.d("yaTaskLog", "result if: $arr ")
                }
                return
            }

        }

        arr.add(note)
        _task.update { arr }.also {
            Log.d("yaTaskLog", "result else: $arr ")
        }
    }


    override fun complectedTaskSize(): Flow<Int> = flow {
        var size = 0
        taskList.forEach{
            if (it.isCompleted){
                size++
            }
        }
        emit(size)
    }


}