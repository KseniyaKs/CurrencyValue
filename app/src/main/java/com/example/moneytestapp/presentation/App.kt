package com.example.moneytestapp.presentation

import android.app.Application
import android.content.Context
import com.example.moneytestapp.di.AppComponent
import com.example.moneytestapp.di.AppModule
import com.example.moneytestapp.di.DaggerAppComponent


class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this.applicationContext))
            .build()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }
