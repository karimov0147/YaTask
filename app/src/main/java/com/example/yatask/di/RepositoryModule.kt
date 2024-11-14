package com.example.yatask.di

import com.example.yatask.data.repository.TodoItemsRepository
import com.example.yatask.data.repository.impl.TodoItemRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindRepository(impl : TodoItemRepositoryImpl) : TodoItemsRepository

}