package com.touki.moviez.presentation.settings

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.touki.moviez.R
import com.touki.moviez.databinding.ActivitySettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {

    private val settingViewModel: SettingsViewModel by viewModels()
    private lateinit var viewBinding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = getString(R.string.label_setting)
        }

        initiateView()
        setDarkThemeObserver()
        setOnClick()
    }

    override fun onNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initiateView() {
        with(viewBinding) {
            switchDarkTheme.isChecked = settingViewModel.isDarkThemeEnabled()
        }
    }

    private fun setDarkThemeObserver() {
        settingViewModel.darkThemeEnabledSuccess.observe(this) {

        }

        settingViewModel.darkThemeEnabledFailure.observe(this) {
            Toast.makeText(this@SettingsActivity, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setOnClick() {
        with(viewBinding) {
            switchDarkTheme.setOnCheckedChangeListener { _, isChecked ->
                settingViewModel.toggleDarkTheme(isChecked)
            }
        }
    }
}