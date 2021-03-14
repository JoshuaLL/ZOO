package com.ddt.zoo.ui.settings

import android.os.Bundle
import android.widget.VideoView
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.ddt.zoo.R
import kotlinx.android.synthetic.main.activity_main.*


class SettingsFragment : PreferenceFragmentCompat() {

    companion object{
        const val KEY_PREFERENCES = "com.ddt.zoo_preferences"
        const val KEY_DAY_NIGHT = "night_mode"
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        requireActivity().toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val nightModePreference: Preference? = findPreference(KEY_DAY_NIGHT)

        nightModePreference?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { preference, newValue ->
                when (newValue.toString().toInt()) {
                    1 -> {
                        setDefaultNightMode(MODE_NIGHT_YES)
                    }
                    2 -> {
                        setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
                    }
                    else -> {
                        setDefaultNightMode(MODE_NIGHT_NO)
                    }
                }
                findNavController().navigateUp()
                true
            }
    }

}