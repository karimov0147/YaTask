package com.example.yatask.ui.models

import com.example.yatask.utils.Importance
import java.io.Serializable
import java.util.Date
data class TodoItem (
    val id : String,
    val text : String,
    val importance : Importance,
    val deadline : Date?,
    val isCompleted : Boolean = false,
    val createdAt : Date,
    val modifiedAt : Date
)