package com.example.moneytestapp.di

import com.example.moneytestapp.MainActivity
import com.example.moneytestapp.presentation.currency_selector.CurrencySelectorFragment
import com.example.moneytestapp.presentation.favorite_screen.FavoriteFragment
import com.example.moneytestapp.presentation.popular_screen.PopularFragment
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        DataBaseModule::class,
        DataModule::class,
        DomainModule::class,
        ViewModelModule::class,
//        NotifyWorkModule::class
    ]
)
@Singleton
interface AppComponent {
    fun inject(fragment: PopularFragment)

    fun inject(fragment: FavoriteFragment)

    fun inject(fragment: CurrencySelectorFragment)

    fun inject(activity: MainActivity)
}