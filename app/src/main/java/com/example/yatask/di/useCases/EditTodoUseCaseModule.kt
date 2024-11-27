package com.example.yatask.di.useCases

import com.example.yatask.domain.useCase.EditTodoUseCase
import com.example.yatask.domain.useCase.Impl.EditTodoUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface EditTodoUseCaseModule {
    @Binds
    @Singleton
    fun bindUseCase(impl: EditTodoUseCaseImpl): EditTodoUseCase

}