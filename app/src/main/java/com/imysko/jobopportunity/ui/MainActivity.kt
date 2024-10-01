package com.imysko.jobopportunity.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.imysko.jobopportunity.App
import com.imysko.jobopportunity.R
import com.imysko.jobopportunity.databinding.ActivityMainBinding
import java.util.Locale
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: MainActivityViewModel.Factory

    private val _viewModel: MainActivityViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as App).appComponent.inject(this)

        super.onCreate(savedInstanceState)

        resources.configuration.setLocale(Locale("ru", "RU"))

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavController()
    }

    private fun setupNavController() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment

        val navController = navHostFragment.navController
        binding.bottomNavBar.setupWithNavController(navController)

        val badge = binding.bottomNavBar.getOrCreateBadge(R.id.navigation_favorite).also {
            it.verticalOffset = 16
            it.horizontalOffset = 8
            it.backgroundColor = resources.getColor(com.imysko.common.ui.R.color.red, null)
            it.badgeTextColor = resources.getColor(com.imysko.common.ui.R.color.white, null)
            it.setTextAppearance(com.imysko.common.ui.R.style.NumberText)
        }

        _viewModel.favoriteVacanciesCount.asLiveData().observe(this) { count ->
            badge.isVisible = count > 0
            badge.number = count
        }
    }
}
