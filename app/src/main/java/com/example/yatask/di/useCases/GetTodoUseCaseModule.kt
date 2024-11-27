package com.example.yatask.di.useCases

import com.example.yatask.domain.useCase.Impl.GetTodoUseCaseImpl
import com.example.yatask.domain.useCase.GetTodoUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface GetTodoUseCaseModule {
    @Binds
    @Singleton
    fun bindUseCase(impl : GetTodoUseCaseImpl) : GetTodoUseCase

}