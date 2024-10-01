package com.imysko.features.favorite.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.imysko.common.ui.delegateAdapter.CompositeAdapter
import com.imysko.common.ui.vacancies.VacancyAdapterDelegate
import com.imysko.features.favorite.R
import com.imysko.features.favorite.databinding.FragmentFavoriteBinding
import com.imysko.features.favorite.di.DaggerFavoriteVacanciesComponent
import com.imysko.features.favorite.di.FavoriteVacanciesDepsStore
import com.imysko.features.favorite.presentation.adapters.BlockTitleAdapterDelegate
import com.imysko.features.favorite.presentation.entities.BlockTitleAdapterModel
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: FavoriteViewModelFactory

    private val _viewModel: FavoriteVewModel by viewModels {
        viewModelFactory
    }

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val compositeAdapter by lazy {
        CompositeAdapter.Builder()
            .add(BlockTitleAdapterDelegate())
            .add(
                VacancyAdapterDelegate(
                    onCardClick = { },
                    onFavoriteButtonClick = ::onVacancyFavoriteButtonClick,
                )
            )
            .build()
    }

    override fun onAttach(context: Context) {
        DaggerFavoriteVacanciesComponent.builder()
            .deps(FavoriteVacanciesDepsStore.deps)
            .build()
            .inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
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
            compositeAdapter.submitList(
                listOf(
                    BlockTitleAdapterModel(
                        title = resources.getString(R.string.favorite_screen_title),
                        count = uiState.favoriteVacanciesCount,
                    ),
                    *uiState.favoriteVacancies.toTypedArray(),
                )
            )
        }
    }

    private fun onVacancyFavoriteButtonClick(id: String, newState: Boolean) {
        _viewModel.deleteVacancyFromFavorite(id)
    }
}