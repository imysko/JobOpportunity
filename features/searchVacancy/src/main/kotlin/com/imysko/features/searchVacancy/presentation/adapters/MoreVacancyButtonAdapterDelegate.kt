package com.imysko.features.searchVacancy.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.imysko.common.ui.delegateAdapter.DelegateAdapter
import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem
import com.imysko.features.searchVacancy.R
import com.imysko.features.searchVacancy.databinding.ButtonItemBinding
import com.imysko.features.searchVacancy.presentation.entities.ButtonAdapterModel

internal class MoreVacancyButtonAdapterDelegate(
    private val onButtonClick: () -> Unit,
) : DelegateAdapter<ButtonAdapterModel, MoreVacancyButtonAdapterDelegate.MoreVacancyButtonViewHolder>(
    ButtonAdapterModel::class.java
) {

    class MoreVacancyButtonViewHolder(
        val binding: ButtonItemBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return MoreVacancyButtonViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.button_item,
                parent,
                false
            )
        )
    }

    override fun bindViewHolder(
        model: ButtonAdapterModel,
        viewHolder: MoreVacancyButtonViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.binding.text = model.text

        viewHolder.binding.moreVacancyButton.setOnClickListener {
            onButtonClick()
        }
    }
}
