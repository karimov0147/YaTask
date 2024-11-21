package com.example.yatask.domain.models

import com.example.yatask.utils.Importance
import java.util.Date

class TodoDomainDTO(
    val id : String,
    val text : String,
    val importance : Importance,
    val deadline : Date?,
    val isCompleted : Boolean = false,
    val createdAt : Date,
    val modifiedAt : Date
)