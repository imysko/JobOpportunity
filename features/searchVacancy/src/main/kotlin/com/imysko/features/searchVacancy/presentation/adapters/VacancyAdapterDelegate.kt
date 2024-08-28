package com.imysko.features.searchVacancy.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.imysko.common.ui.delegateAdapter.DelegateAdapter
import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem
import com.imysko.features.searchVacancy.R
import com.imysko.features.searchVacancy.databinding.VacancyCardBinding
import com.imysko.features.searchVacancy.presentation.entities.VacancyAdapterModel

internal class VacancyAdapterDelegate(
    private val onCardClick: (id: String) -> Unit,
    private val onFavoriteButtonClick: (id: String, newState: Boolean) -> Unit,
) : DelegateAdapter<VacancyAdapterModel, VacancyAdapterDelegate.VacancyCardViewHolder>(
    VacancyAdapterModel::class.java
) {

    class VacancyCardViewHolder(
        val binding: VacancyCardBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return VacancyCardViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vacancy_card,
                parent,
                false
            )
        )
    }

    override fun bindViewHolder(
        model: VacancyAdapterModel,
        viewHolder: VacancyCardViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.binding.vacancyInfo = model

        viewHolder.binding.vacancyCard.setOnClickListener {
            onCardClick(model.id)
        }
        viewHolder.binding.favoriteButton.setOnClickListener {
            onFavoriteButtonClick(model.id, !model.isFavorite)
        }
    }
}
