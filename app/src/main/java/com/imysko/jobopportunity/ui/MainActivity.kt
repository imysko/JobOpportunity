package com.imysko.jobopportunity.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.replace
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.imysko.jobopportunity.App
import com.imysko.jobopportunity.R
import com.imysko.jobopportunity.databinding.ActivityMainBinding
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

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _viewModel.isAuthorized.asLiveData().observe(this) { authState ->
            if (authState) {
                supportFragmentManager.beginTransaction()
                    .replace<MainFragment>(R.id.main_container)
                    .runOnCommit {
                        setupNavController()
                    }
                    .commitNow()
            } else {
                supportFragmentManager.beginTransaction()
                    .replace<AuthorizationFragment>(R.id.main_container)
                    .commitNow()
            }
        }
    }

    private fun setupNavController() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_container)
            ?.childFragmentManager
            ?.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment

        val navController = navHostFragment.navController
        binding.bottomNavBar.setupWithNavController(navController)
    }
}
