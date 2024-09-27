package com.imysko.features.searchVacancy.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.imysko.common.ui.delegateAdapter.DelegateAdapter
import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem
import com.imysko.features.searchVacancy.R
import com.imysko.features.searchVacancy.databinding.TotalVacanciesDescriptionBinding
import com.imysko.features.searchVacancy.presentation.entities.TotalVacanciesDescriptionAdapterModel

internal class TotalVacanciesDescriptionAdapterDelegate :
    DelegateAdapter<TotalVacanciesDescriptionAdapterModel, TotalVacanciesDescriptionAdapterDelegate.TotalVacanciesDescriptionViewHolder>(
        TotalVacanciesDescriptionAdapterModel::class.java
    ) {

    class TotalVacanciesDescriptionViewHolder(
        val binding: TotalVacanciesDescriptionBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return TotalVacanciesDescriptionViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.total_vacancies_description,
                parent,
                false
            )
        )
    }

    override fun bindViewHolder(
        model: TotalVacanciesDescriptionAdapterModel,
        viewHolder: TotalVacanciesDescriptionViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.binding.vacanciesCount = model.totalVacanciesCount
        viewHolder.binding.sortType = model.sortType
    }
}
