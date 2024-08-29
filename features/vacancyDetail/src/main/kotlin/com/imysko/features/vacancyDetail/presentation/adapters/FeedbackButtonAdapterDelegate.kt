package com.imysko.features.vacancyDetail.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.imysko.common.ui.delegateAdapter.DelegateAdapter
import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem
import com.imysko.features.vacancyDetail.R
import com.imysko.features.vacancyDetail.databinding.VacancyButtonItemBinding
import com.imysko.features.vacancyDetail.presentation.entities.ButtonAdapterModel

internal class FeedbackButtonAdapterDelegate(
    private val onButtonClick: () -> Unit,
) : DelegateAdapter<ButtonAdapterModel, FeedbackButtonAdapterDelegate.FeedbackButtonViewHolder>(
    ButtonAdapterModel::class.java
) {

    class FeedbackButtonViewHolder(
        val binding: VacancyButtonItemBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return FeedbackButtonViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vacancy_button_item,
                parent,
                false
            )
        )
    }

    override fun bindViewHolder(
        model: ButtonAdapterModel,
        viewHolder: FeedbackButtonViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.binding.text = model.text

        viewHolder.binding.moreVacancyButton.setOnClickListener {
            onButtonClick()
        }
    }
}
