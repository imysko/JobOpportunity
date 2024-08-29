package com.imysko.features.vacancyDetail.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.imysko.common.ui.delegateAdapter.CompositeAdapter
import com.imysko.features.vacancyDetail.R
import com.imysko.features.vacancyDetail.databinding.FragmentVacancyDetailBinding
import com.imysko.features.vacancyDetail.di.DaggerVacancyDetailComponent
import com.imysko.features.vacancyDetail.di.VacancyDetailDepsStore
import com.imysko.features.vacancyDetail.presentation.adapters.CompanyInfoAdapterDelegate
import com.imysko.features.vacancyDetail.presentation.adapters.FeedbackButtonAdapterDelegate
import com.imysko.features.vacancyDetail.presentation.adapters.VacancyHeaderAdapterDelegate
import com.imysko.features.vacancyDetail.presentation.adapters.VacancyTextBlockAdapterDelegate
import com.imysko.features.vacancyDetail.presentation.entities.ButtonAdapterModel
import com.imysko.features.vacancyDetail.presentation.entities.VacancyTextBlockAdapterModel
import javax.inject.Inject

class VacancyDetailFragment : Fragment() {

    private val _args by navArgs<VacancyDetailFragmentArgs>()

    @Inject
    lateinit var factory: VacancyDetailViewModelFactory.Factory

    private val _viewModel: VacancyDetailViewModel by viewModels {
        factory.create(_args.vacancyId)
    }

    private var _binding: FragmentVacancyDetailBinding? = null
    private val binding get() = _binding!!

    private val compositeAdapter by lazy {
        CompositeAdapter.Builder()
            .add(VacancyHeaderAdapterDelegate())
            .add(CompanyInfoAdapterDelegate())
            .add(VacancyTextBlockAdapterDelegate())
            .add(FeedbackButtonAdapterDelegate(::onFeedbackButtonClick))
            .build()
    }

    override fun onAttach(context: Context) {
        DaggerVacancyDetailComponent.builder()
            .deps(VacancyDetailDepsStore.deps)
            .build()
            .inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVacancyDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        requireActivity().onBackPressedDispatcher.addCallback(
            this.viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navigateBack()
                }
            }
        )

        _viewModel.uiState.asLiveData().observe(viewLifecycleOwner) { uiState ->
            updateCompositeAdapter(uiState)
        }
        binding.recyclerView.adapter = compositeAdapter

        return root
    }

    private fun updateCompositeAdapter(uiState: VacancyDetailUiState) {
        val compositeList = mutableListOf(
            uiState.vacancyHeader,
            uiState.companyInfo,
        )

        uiState.description?.let {
            compositeList.add(VacancyTextBlockAdapterModel(text = it))
        }
        uiState.responsibilities?.let {
            compositeList.add(
                VacancyTextBlockAdapterModel(
                    title = resources.getString(R.string.yours_tasks),
                    text = it,
                )
            )
        }

        compositeList.add(ButtonAdapterModel(resources.getString(R.string.feedback_button)))

        compositeAdapter.submitList(compositeList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateBack() {
        findNavController().navigateUp()
    }

    private fun onFeedbackButtonClick() {
        // TODO: open feedback modal
    }
}
