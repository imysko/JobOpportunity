package com.imysko.jobopportunity.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
    }
}
