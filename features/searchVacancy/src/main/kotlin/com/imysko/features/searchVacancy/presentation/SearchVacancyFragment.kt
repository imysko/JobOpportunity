package com.imysko.features.searchVacancy.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.imysko.common.ui.delegateAdapter.CompositeAdapter
import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem
import com.imysko.features.searchVacancy.R
import com.imysko.features.searchVacancy.databinding.FragmentSearchVacancyBinding
import com.imysko.features.searchVacancy.di.DaggerSearchVacancyComponent
import com.imysko.features.searchVacancy.di.SearchVacancyDepsStore
import com.imysko.features.searchVacancy.presentation.adapters.BlockTitleAdapterDelegate
import com.imysko.features.searchVacancy.presentation.adapters.MoreVacancyButtonAdapterDelegate
import com.imysko.features.searchVacancy.presentation.adapters.OffersBlockAdapterDelegate
import com.imysko.features.searchVacancy.presentation.adapters.VacancyAdapterDelegate
import com.imysko.features.searchVacancy.presentation.entities.BlockTitleAdapterModel
import com.imysko.features.searchVacancy.presentation.entities.ButtonAdapterModel
import com.imysko.features.searchVacancy.presentation.entities.OffersBlockAdapterModel
import javax.inject.Inject

class SearchVacancyFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: SearchVacancyViewModelFactory

    private val _viewModel: SearchVacancyViewModel by viewModels {
        viewModelFactory
    }

    private var _binding: FragmentSearchVacancyBinding? = null
    private val binding get() = _binding!!

    private val compositeAdapter by lazy {
        CompositeAdapter.Builder()
            .add(
                OffersBlockAdapterDelegate(
                    onCardClick = ::onOfferCardClick,
                )
            )
            .add(BlockTitleAdapterDelegate())
            .add(
                VacancyAdapterDelegate(
                    onCardClick = ::onVacancyCardClick,
                    onFavoriteButtonClick = ::onVacancyFavoriteButtonClick,
                )
            )
            .add(MoreVacancyButtonAdapterDelegate(::onMoreVacancyButtonClick))
            .build()
    }

    override fun onAttach(context: Context) {
        DaggerSearchVacancyComponent.builder()
            .deps(SearchVacancyDepsStore.deps)
            .build()
            .inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchVacancyBinding.inflate(inflater, container, false)
        val root: View = binding.root

        updateCompositeAdapter()
        binding.recyclerView.adapter = compositeAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateCompositeAdapter() {
        _viewModel.uiState.asLiveData().observe(viewLifecycleOwner) { uiState ->
            if (uiState.isShowOffersList) {
                submitCompositeAdapter(
                    OffersBlockAdapterModel(uiState.offersList),
                    BlockTitleAdapterModel(resources.getString(R.string.block_vacancies_title)),
                    *uiState.vacanciesList.toTypedArray(),
                    ButtonAdapterModel(
                        resources.getQuantityString(
                            R.plurals.more_vacancy,
                            uiState.totalVacancies,
                            uiState.totalVacancies
                        )
                    ),
                )
            } else {
                submitCompositeAdapter(
                    BlockTitleAdapterModel(resources.getString(R.string.block_vacancies_title)),
                    *uiState.vacanciesList.toTypedArray(),
                    ButtonAdapterModel(
                        resources.getQuantityString(
                            R.plurals.more_vacancy,
                            uiState.totalVacancies,
                            uiState.totalVacancies
                        )
                    ),
                )
            }
        }
    }

    private fun submitCompositeAdapter(vararg items: DelegateAdapterItem) {
        compositeAdapter.submitList(items.toList())
    }

    private fun onOfferCardClick(link: String) {
        val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(urlIntent)
    }

    private fun onVacancyCardClick(id: String) {
        // TODO: navigate to vacancy detail screen
    }

    private fun onVacancyFavoriteButtonClick(id: String, newState: Boolean) {
        _viewModel.changeVacancyFavoriteState(id, newState)
    }

    private fun onMoreVacancyButtonClick() {
        // TODO: change screen state
    }
}
