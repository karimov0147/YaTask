package com.example.yatask.data.source.remote.models

import com.google.gson.annotations.SerializedName


data class TodoBaseRequest<T> (
    @SerializedName("element")
    val element : T
)