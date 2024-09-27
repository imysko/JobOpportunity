package com.imysko.features.searchVacancy.presentation.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.imysko.common.ui.delegateAdapter.DelegateAdapter
import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem
import com.imysko.features.searchVacancy.R
import com.imysko.features.searchVacancy.databinding.SearchBlockBinding
import com.imysko.features.searchVacancy.presentation.entities.SearchBlockAdapterModel

internal class SearchBlockAdapterDelegate :
    DelegateAdapter<SearchBlockAdapterModel, SearchBlockAdapterDelegate.SearchBlockViewHolder>(
        SearchBlockAdapterModel::class.java
    ) {

    class SearchBlockViewHolder(
        val binding: SearchBlockBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return SearchBlockViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.search_block,
                parent,
                false
            )
        )
    }

    override fun bindViewHolder(
        model: SearchBlockAdapterModel,
        viewHolder: SearchBlockViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.binding.hint = model.hint

        viewHolder.binding.searchLineLayout.setStartIconDrawable(model.startIconDrawableRes)

        if (model.startIconAction != null) {
            viewHolder.binding.searchLineLayout.setStartIconTintList(
                ColorStateList.valueOf(
                    viewHolder.itemView.context.getColor(com.imysko.common.ui.R.color.white)
                )
            )
            viewHolder.binding.searchLineLayout.setStartIconOnClickListener {
                model.startIconAction.invoke()
            }
        } else {
            viewHolder.binding.searchLineLayout.setStartIconTintList(
                ColorStateList.valueOf(
                    viewHolder.itemView.context.getColor(com.imysko.common.ui.R.color.grey4)
                )
            )
        }
    }
}
