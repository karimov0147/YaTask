package com.example.yatask.utils

import com.example.yatask.data.source.remote.models.TodoRequestDTO
import com.example.yatask.data.source.remote.models.TodoResponseDTO
import com.example.yatask.domain.models.TodoDomainDTO
import com.example.yatask.ui.models.TodoItem
import java.util.Date

fun TodoItem.toDomainDTO() = TodoDomainDTO(id , text , importance , deadline , isCompleted , createdAt , modifiedAt )

fun TodoDomainDTO.toRequestDTO() = TodoRequestDTO(id , text , importance.name.lowercase() , deadline?.time , isCompleted , createdAt.time , modifiedAt.time )

fun TodoResponseDTO.toDomainDTO() = TodoDomainDTO(id, text,
    if (importance == "HIGH") Importance.HIGH else if (importance == "NORMAL") Importance.BASIC else Importance.LOW
    , Date(deadline?:0), isDone , Date(createdDate) , Date(changedDate) )

fun TodoDomainDTO.toTodoItem() = TodoItem(id, text, importance, deadline, isCompleted, createdAt, modifiedAt)

