package com.ddt.zoo.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.ddt.zoo.R
import com.ddt.zoo.ui.settings.SettingsFragment.Companion.KEY_DAY_NIGHT
import com.ddt.zoo.ui.settings.SettingsFragment.Companion.KEY_PREFERENCES
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        getSharedPreferences(KEY_PREFERENCES, MODE_PRIVATE).let {
           it.getString(KEY_DAY_NIGHT, "0").let {

               Timber.i("KEY_PREFERENCES: $it")
               AppCompatDelegate.setDefaultNightMode(
                   when (it.toString().toInt()) {
                       1 -> AppCompatDelegate.MODE_NIGHT_YES
                       2 -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                       else -> AppCompatDelegate.MODE_NIGHT_NO
                   }
               )
           }
        }
    }



}