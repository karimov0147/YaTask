package com.example.yatask.di.repositories

import com.example.yatask.domain.repository.TodoItemsRepository
import com.example.yatask.data.repository.TodoItemRepositoryImpl
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