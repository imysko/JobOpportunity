package com.imysko.features.vacancyDetail.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.imysko.common.ui.delegateAdapter.DelegateAdapter
import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem
import com.imysko.features.vacancyDetail.R
import com.imysko.features.vacancyDetail.databinding.CompanyInfoItemBinding
import com.imysko.features.vacancyDetail.presentation.entities.CompanyInfoAdapterModel

class CompanyInfoAdapterDelegate :
    DelegateAdapter<CompanyInfoAdapterModel, CompanyInfoAdapterDelegate.CompanyInfoBlockViewHolder>(
        CompanyInfoAdapterModel::class.java
    ) {
    class CompanyInfoBlockViewHolder(
        val binding: CompanyInfoItemBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return CompanyInfoBlockViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.company_info_item,
                parent,
                false
            )
        )
    }

    override fun bindViewHolder(
        model: CompanyInfoAdapterModel,
        viewHolder: CompanyInfoBlockViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.binding.companyInfo = model
    }
}
