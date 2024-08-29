package com.imysko.features.vacancyDetail.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.imysko.common.ui.delegateAdapter.DelegateAdapter
import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem
import com.imysko.features.vacancyDetail.R
import com.imysko.features.vacancyDetail.databinding.VacancyTextBlockItemBinding
import com.imysko.features.vacancyDetail.presentation.entities.VacancyTextBlockAdapterModel

internal class VacancyTextBlockAdapterDelegate :
    DelegateAdapter<VacancyTextBlockAdapterModel, VacancyTextBlockAdapterDelegate.VacancyTextBlockViewHolder>(
        VacancyTextBlockAdapterModel::class.java
    ) {

    class VacancyTextBlockViewHolder(
        val binding: VacancyTextBlockItemBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return VacancyTextBlockViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vacancy_text_block_item,
                parent,
                false
            )
        )
    }

    override fun bindViewHolder(
        model: VacancyTextBlockAdapterModel,
        viewHolder: VacancyTextBlockViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.binding.vacancyTextBlockInfo = model
    }
}
