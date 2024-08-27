package com.imysko.features.authorization.presentation.screens.codeConfirmation

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputEditText
import com.imysko.features.authorization.R
import com.imysko.features.authorization.databinding.FragmentCodeConfirmationBinding
import com.imysko.features.authorization.di.AuthorizationDepsStore
import com.imysko.features.authorization.di.DaggerAuthorizationComponent
import com.imysko.features.authorization.presentation.screens.codeConfirmation.entities.CodeConfirmationDigitIndex
import javax.inject.Inject

class CodeConfirmationFragment : Fragment() {

    private val _args by navArgs<CodeConfirmationFragmentArgs>()

    @Inject
    lateinit var factory: CodeConfirmationViewModelFactory.Factory

    private val _viewModel: CodeConfirmationViewModel by viewModels {
        factory.create(_args.email)
    }

    private var _binding: FragmentCodeConfirmationBinding? = null
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
        _binding = FragmentCodeConfirmationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _viewModel.uiState.asLiveData().observe(viewLifecycleOwner) { uiState ->
            binding.uiState = uiState
        }

        bindCodeConfirmationDigit(binding.firstDigit, CodeConfirmationDigitIndex.FIRST)
        bindCodeConfirmationDigit(binding.secondDigit, CodeConfirmationDigitIndex.SECOND)
        bindCodeConfirmationDigit(binding.thirdDigit, CodeConfirmationDigitIndex.THIRD)
        bindCodeConfirmationDigit(binding.forthDigit, CodeConfirmationDigitIndex.FORTH)

        binding.confirmButton.setOnClickListener {
            _viewModel.authorize()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bindCodeConfirmationDigit(
        digit: TextInputEditText,
        index: CodeConfirmationDigitIndex,
    ) {
        digit.apply {
            doAfterTextChanged { digit ->
                if (digit?.any() == true) {
                    _viewModel.appendDigit(index.index, digit.toString())
                    focusSearch(View.FOCUS_RIGHT)?.requestFocus()
                }
            }

            setOnFocusChangeListener { _, hasFocus ->
                hint = if (hasFocus) {
                    null
                } else {
                    resources.getString(R.string.password_hint)
                }
            }

            setOnKeyListener { _, keyCode, event ->
                when (keyCode) {
                    KeyEvent.KEYCODE_DEL -> {
                        if (event.action == KeyEvent.ACTION_UP) {
                            if (text?.isEmpty() == true) {
                                _viewModel.removeDigit(index.index - 1)
                                focusSearch(View.FOCUS_LEFT)?.requestFocus()
                            } else {
                                _viewModel.removeDigit(index.index)
                            }
                        }
                        return@setOnKeyListener true
                    }

                    else -> false
                }
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
    }
}
