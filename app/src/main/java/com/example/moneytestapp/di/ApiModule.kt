package com.example.moneytestapp.di

import android.content.Context
import com.example.moneytestapp.BuildConfig
import com.example.moneytestapp.data.network.Api
import com.example.moneytestapp.data.network.ResponseMapper
import com.example.moneytestapp.data.network.ResponseMapperImpl
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoSet
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
//
//@Module
@InstallIn(SingletonComponent::class)
@Module //(includes = [ApiModule::class])
//@DisableInstallInCheck
class ApiModule {

    @Singleton
    @Provides
    fun provideApi(
        okHttpClient: OkHttpClient,
//        context: Context,
        moshi: Moshi
    ): Api {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.apilayer.com/")
            .addConverterFactory(GsonConverterFactory.create())
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(Api::class.java)

    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
//            .add(Date::class.java, DateJsonAdapter().nullSafe())
            .build()
    }


    @Provides
    @Singleton
    fun provideResponseMapper(): ResponseMapper {
        return ResponseMapperImpl()
    }
}
