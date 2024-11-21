package com.example.yatask.di.useCases

import com.example.yatask.domain.useCase.Impl.RemoveTodoUseCaseImpl
import com.example.yatask.domain.useCase.RemoveTodoUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RemoveTodoUseCaseModule {
    @Binds
    @Singleton
    fun bindUseCase(impl : RemoveTodoUseCaseImpl) : RemoveTodoUseCase

}