package com.example.yatask.app

import android.app.Application
import com.example.yatask.data.repository.impl.TodoItemRepositoryImpl
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}