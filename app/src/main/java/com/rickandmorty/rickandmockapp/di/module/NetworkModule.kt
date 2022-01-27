package com.rickandmorty.rickandmockapp.di.module

import com.rickandmorty.rickandmockapp.BuildConfig
import com.rickandmorty.rickandmockapp.data.datasource.CharactersDataSource
import com.rickandmorty.rickandmockapp.utils.TIMEOUT_SECONDS
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }

    @Singleton
    @Provides
    internal fun provideBaseRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    internal fun provideCharactersAPI(retrofit: Retrofit): CharactersDataSource =
        retrofit.create(CharactersDataSource::class.java)

}