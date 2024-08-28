package com.imysko.features.searchVacancy.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.imysko.common.ui.delegateAdapter.DelegateAdapter
import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem
import com.imysko.features.searchVacancy.R
import com.imysko.features.searchVacancy.databinding.BlockTitleItemBinding
import com.imysko.features.searchVacancy.presentation.entities.BlockTitleAdapterModel

internal class BlockTitleAdapterDelegate :
    DelegateAdapter<BlockTitleAdapterModel, BlockTitleAdapterDelegate.BlockTitleViewHolder>(
        BlockTitleAdapterModel::class.java
    ) {

    class BlockTitleViewHolder(
        val binding: BlockTitleItemBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return BlockTitleViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.block_title_item,
                parent,
                false
            )
        )
    }

    override fun bindViewHolder(
        model: BlockTitleAdapterModel,
        viewHolder: BlockTitleViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.binding.title = model.title
    }
}
