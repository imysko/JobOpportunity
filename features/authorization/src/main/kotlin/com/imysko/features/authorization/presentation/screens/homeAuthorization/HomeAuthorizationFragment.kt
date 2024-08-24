package com.imysko.features.authorization.presentation.screens.homeAuthorization

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.imysko.features.authorization.databinding.FragmentHomeAuthorizationBinding
import com.imysko.features.authorization.di.DaggerAuthorizationComponent
import javax.inject.Inject

class HomeAuthorizationFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: HomeAuthorizationViewModel.Factory

    private val _viewModel: HomeAuthorizationViewModel by viewModels {
        viewModelFactory
    }

    private var _binding: FragmentHomeAuthorizationBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        DaggerAuthorizationComponent.builder()
            .build()
            .inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeAuthorizationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _viewModel.uiState.asLiveData().observe(viewLifecycleOwner) { uiState ->
            binding.uiState = uiState
        }

        binding.emailInput.doAfterTextChanged { text ->
            _viewModel.onEmailChange(text.toString())
        }

        binding.continueButton.setOnClickListener {
            if (_viewModel.validateEmail()) {
                _viewModel.sendCodeOnEmail()
                TODO("navigate to code confirmation screen")
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
