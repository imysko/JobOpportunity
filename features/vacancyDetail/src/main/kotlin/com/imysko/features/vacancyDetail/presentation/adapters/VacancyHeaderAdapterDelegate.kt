package com.imysko.features.vacancyDetail.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.imysko.common.ui.delegateAdapter.DelegateAdapter
import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem
import com.imysko.features.vacancyDetail.R
import com.imysko.features.vacancyDetail.databinding.VacancyHeaderItemBinding
import com.imysko.features.vacancyDetail.presentation.entities.VacancyHeaderAdapterModel

internal class VacancyHeaderAdapterDelegate :
    DelegateAdapter<VacancyHeaderAdapterModel, VacancyHeaderAdapterDelegate.VacancyHeaderViewHolder>(
        VacancyHeaderAdapterModel::class.java
    ) {

    class VacancyHeaderViewHolder(
        val binding: VacancyHeaderItemBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return VacancyHeaderViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vacancy_header_item,
                parent,
                false
            )
        )
    }

    override fun bindViewHolder(
        model: VacancyHeaderAdapterModel,
        viewHolder: VacancyHeaderViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.binding.vacancyHeaderInfo = model
    }
}
