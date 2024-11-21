package com.example.yatask.di.useCases

import com.example.yatask.domain.useCase.AddTodoUseCase
import com.example.yatask.domain.useCase.Impl.AddTodoUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface AddTodoUseCaseModule {
    @Binds
    @Singleton
    fun bindUseCase(impl : AddTodoUseCaseImpl) : AddTodoUseCase

}