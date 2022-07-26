package com.example.moneytestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.moneytestapp.presentation.favorite_screen.FavoriteFragment
import com.example.moneytestapp.presentation.popular_screen.PopularFragment
import com.example.moneytestapp.presentation.popular_screen_new.PopularFragmentNew
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint()
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.actionPopular -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, PopularFragment.newInstance())
                        .commit()
                    true
                }
                R.id.actionFavorite -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, FavoriteFragment.newInstance())
                        .commit()
                    true
                }
                else -> false
            }
        }
        bottomNavigationView.selectedItemId = R.id.actionPopular
    }

}