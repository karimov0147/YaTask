package com.example.yatask.di.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.yatask.utils.Revision
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class InterceptorModule {

    @Provides
    @Singleton
    fun client( @ApplicationContext context: Context) : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor.Builder(context).build())
        .addInterceptor {  chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("Authorization", "Bearer Elrohir")
                .header("X-Last-Known-Revision" , Revision.revison.toString())
                .method(original.method() , original.body())
                .build()
            chain.proceed(request)
        }
        .readTimeout(30 , TimeUnit.SECONDS)
        .writeTimeout(30 , TimeUnit.SECONDS)
        .build()
}