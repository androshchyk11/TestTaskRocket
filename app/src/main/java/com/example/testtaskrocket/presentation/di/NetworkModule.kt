package com.example.testtaskrocket.presentation.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.testtaskrocket.BuildConfig
import com.example.testtaskrocket.data.api.ApiRequestService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        private const val GENERAL_TIMEOUT = 25L
        internal const val CONNECT_TIMEOUT = GENERAL_TIMEOUT
        internal const val WRITE_TIMEOUT = GENERAL_TIMEOUT
        internal const val READ_TIMEOUT = GENERAL_TIMEOUT
    }


    @Provides
    fun provideChuckerInterceptor(@ApplicationContext context: Context) =
        ChuckerInterceptor.Builder(context).build()

    @Provides
    fun provideOkHttpClient(
        chuckInterceptor: ChuckerInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(chuckInterceptor)
            .build()


    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BuildConfig.MAIN_URL)
            .build()

    @Provides
    @Singleton
    fun provideApiRequestService(retrofit: Retrofit): ApiRequestService =
        retrofit.create(ApiRequestService::class.java)
}