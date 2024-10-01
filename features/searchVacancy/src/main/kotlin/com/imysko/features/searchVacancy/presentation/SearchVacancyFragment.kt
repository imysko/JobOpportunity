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
import com.imysko.common.ui.vacancies.VacancyAdapterDelegate
import com.imysko.features.searchVacancy.R
import com.imysko.features.searchVacancy.databinding.FragmentSearchVacancyBinding
import com.imysko.features.searchVacancy.di.DaggerSearchVacancyComponent
import com.imysko.features.searchVacancy.di.SearchVacancyDepsStore
import com.imysko.features.searchVacancy.presentation.adapters.BlockTitleAdapterDelegate
import com.imysko.features.searchVacancy.presentation.adapters.MoreVacancyButtonAdapterDelegate
import com.imysko.features.searchVacancy.presentation.adapters.OffersBlockAdapterDelegate
import com.imysko.features.searchVacancy.presentation.adapters.SearchBlockAdapterDelegate
import com.imysko.features.searchVacancy.presentation.adapters.TotalVacanciesDescriptionAdapterDelegate
import com.imysko.features.searchVacancy.presentation.entities.BlockTitleAdapterModel
import com.imysko.features.searchVacancy.presentation.entities.ButtonAdapterModel
import com.imysko.features.searchVacancy.presentation.entities.OffersBlockAdapterModel
import com.imysko.features.searchVacancy.presentation.entities.SearchBlockAdapterModel
import com.imysko.features.searchVacancy.presentation.entities.TotalVacanciesDescriptionAdapterModel
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
            .add(SearchBlockAdapterDelegate())
            .add(TotalVacanciesDescriptionAdapterDelegate())
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
            when (uiState.screenState) {
                SearchVacancyUiState.ScreenState.MainState -> {
                    submitCompositeAdapter(
                        SearchBlockAdapterModel(
                            startIconDrawableRes = com.imysko.common.ui.R.drawable.search,
                            hint = resources.getString(R.string.search_line_hint),
                        ),
                        OffersBlockAdapterModel(uiState.offersList),
                        BlockTitleAdapterModel(resources.getString(R.string.block_vacancies_title)),
                        *uiState.vacanciesList.take(3).toTypedArray(),
                        ButtonAdapterModel(
                            resources.getQuantityString(
                                R.plurals.more_vacancy,
                                uiState.totalVacancies,
                                uiState.totalVacancies
                            )
                        ),
                    )
                }

                SearchVacancyUiState.ScreenState.VacanciesByMatch -> {
                    submitCompositeAdapter(
                        SearchBlockAdapterModel(
                            startIconDrawableRes = com.imysko.common.ui.R.drawable.back_arrow,
                            startIconAction = ::onBackVacancyButtonClick,
                            hint = resources.getString(R.string.search_line_hint_by_matches_vacancies),
                        ),
                        TotalVacanciesDescriptionAdapterModel(
                            totalVacanciesCount = uiState.totalVacancies,
                            sortType = resources.getString(R.string.sort_by_matches)
                        ),
                        *uiState.vacanciesList.toTypedArray(),
                    )
                }
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

    private fun onVacancyCardClick(id: String) = Unit

    private fun onVacancyFavoriteButtonClick(id: String, newState: Boolean) {
        if (newState) {
            _viewModel.addVacancyToFavorite(id)
        } else {
            _viewModel.deleteVacancyFromFavorite(id)
        }
    }

    private fun onMoreVacancyButtonClick() {
        _viewModel.showVacanciesByMatch()
    }

    private fun onBackVacancyButtonClick() {
        _viewModel.backToMainVacanciesState()
    }
}
