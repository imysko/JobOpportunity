package com.imysko.features.searchVacancy.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.imysko.common.ui.delegateAdapter.DelegateAdapter
import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem
import com.imysko.features.searchVacancy.R
import com.imysko.features.searchVacancy.databinding.OffersBlockItemBinding
import com.imysko.features.searchVacancy.presentation.entities.OffersBlockAdapterModel

internal class OffersBlockAdapterDelegate(
    private val onCardClick: (link: String) -> Unit,
) : DelegateAdapter<OffersBlockAdapterModel, OffersBlockAdapterDelegate.OffersBlockViewHolder>(
    OffersBlockAdapterModel::class.java
) {

    private val offersListAdapter = OffersListAdapter(onCardClick = { onCardClick(it) })

    class OffersBlockViewHolder(
        val binding: OffersBlockItemBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return OffersBlockViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.offers_block_item,
                parent,
                false
            )
        )
    }

    override fun bindViewHolder(
        model: OffersBlockAdapterModel,
        viewHolder: OffersBlockViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        offersListAdapter.updateList(model.offersList)

        viewHolder.binding.adapter = offersListAdapter
    }
}
