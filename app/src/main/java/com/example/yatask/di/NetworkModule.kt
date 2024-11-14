package com.example.yatask.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun client( @ApplicationContext context: Context) : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor.Builder(context).build())
        .addInterceptor {  chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("Authorization", "Elrohir")
                .method(original.method() , original.body())
                .build()
            chain.proceed(request)
        }
        .readTimeout(30 , TimeUnit.SECONDS)
        .writeTimeout(30 , TimeUnit.SECONDS)
        .build()


    @Provides
    @Singleton
    fun getNetworkHelper(client : OkHttpClient) : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl("https://hive.mrdekk.ru")
        .build()

}