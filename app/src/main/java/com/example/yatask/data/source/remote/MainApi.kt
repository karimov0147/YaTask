package com.example.yatask.data.source.remote

import retrofit2.http.GET

interface MainApi {

    @GET("todo/list")
    fun getAllList()


    // uvi ne uspel realizovat ostalniye chasti 🥲🥲


}