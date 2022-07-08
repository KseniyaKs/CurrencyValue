package com.example.moneytestapp.di

import android.app.Application
import android.content.Context
import com.example.moneytestapp.presentation.App
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

//@Singleton
//@Component(
//    modules = [
////        AndroidInjectionModule::class,
////        AppModule::class,
////        CommonModule::class,
////        SessionModule::class,
//        ApiModule::class,
//        DataModule::class,
//        DomainModule::class,
////        MapperModule::class,
////        ActivityModule::class,
////        ServiceModule::class
//    ]
//)
//internal interface AppComponent : AndroidInjector<App> {
//    @Component.Factory
//    abstract class AndroidInjectorBuilder : AndroidInjector.Factory<App>
//}

//@Module
//interface AppModule {
//
//    @Binds
//    fun bindApplication(app: App): Application
//
//    @Binds
//    fun bindContext(app: App): Context
//
//}