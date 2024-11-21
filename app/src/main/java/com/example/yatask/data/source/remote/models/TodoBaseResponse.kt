package com.example.yatask.data.source.remote.models

import com.google.gson.annotations.SerializedName

data class TodoBaseResponse<T> (
    @SerializedName("status")
    val status : String,
    @SerializedName("revision")
    val revision : Int,
    @SerializedName(value = "list" , alternate = ["element"])
    val data: T
)