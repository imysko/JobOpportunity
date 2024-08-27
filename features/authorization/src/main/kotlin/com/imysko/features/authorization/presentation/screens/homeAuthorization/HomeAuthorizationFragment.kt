package com.imysko.features.authorization.presentation.screens.homeAuthorization

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.imysko.features.authorization.databinding.FragmentHomeAuthorizationBinding
import com.imysko.features.authorization.di.AuthorizationDepsStore
import com.imysko.features.authorization.di.DaggerAuthorizationComponent
import javax.inject.Inject


class HomeAuthorizationFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: HomeAuthorizationViewModelFactory

    private val _viewModel: HomeAuthorizationViewModel by viewModels {
        viewModelFactory
    }

    private var _binding: FragmentHomeAuthorizationBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        DaggerAuthorizationComponent.builder()
            .deps(AuthorizationDepsStore.deps)
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

        binding.emailInput.apply {
            doAfterTextChanged { text ->
                _viewModel.onEmailChange(text.toString())
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> {
                        val imm =
                            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(view?.windowToken, 0)
                        clearFocus()

                        return@setOnEditorActionListener true
                    }

                    else -> false
                }
            }
        }

        binding.continueButton.setOnClickListener {
            if (_viewModel.validateEmail()) {
                _viewModel.sendCodeOnEmail()

                navigateToCodeConfirmationScreen()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToCodeConfirmationScreen() {
        val action =
            HomeAuthorizationFragmentDirections.actionNavigateToCodeConfirmation(_viewModel.emailInput)
        findNavController().navigate(action)
    }
}
