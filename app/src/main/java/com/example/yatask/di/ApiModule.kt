package com.example.yatask.di

import com.example.yatask.data.source.remote.TodoApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @[Provides Singleton]
    fun providerApi(retrofit: Retrofit) : TodoApiService = retrofit.create(TodoApiService::class.java)


}