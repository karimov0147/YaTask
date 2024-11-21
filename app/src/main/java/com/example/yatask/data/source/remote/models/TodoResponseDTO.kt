package com.example.yatask.data.source.remote.models

import com.google.gson.annotations.SerializedName

data class TodoResponseDTO(
    @SerializedName("id")
    val id : String ,
    @SerializedName("text")
    val text : String ,
    @SerializedName("importance")
    val importance : String ,
    @SerializedName("deadline")
    val deadline : Long?,
    @SerializedName("done")
    val isDone : Boolean,
    @SerializedName("created_at")
    val createdDate : Long ,
    @SerializedName("changed_at")
    val changedDate : Long ,
    @SerializedName("last_updated_by")
    val lastUpdatedId : String
)