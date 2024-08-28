package com.imysko.features.searchVacancy.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.imysko.features.searchVacancy.R
import com.imysko.features.searchVacancy.databinding.OfferCardBinding
import com.imysko.features.searchVacancy.presentation.entities.OfferAdapterModel

internal class OffersListAdapter(
    private val onCardClick: (link: String) -> Unit,
) : RecyclerView.Adapter<OffersListAdapter.OfferCardViewHolder>() {

    private val _offersList: MutableList<OfferAdapterModel> = mutableListOf()

    class OfferCardViewHolder(
        val binding: OfferCardBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferCardViewHolder {
        return OfferCardViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.offer_card,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = _offersList.size

    override fun onBindViewHolder(holder: OfferCardViewHolder, position: Int) {
        val offer = _offersList[position]

        holder.binding.offerInfo = offer

        holder.binding.offerCard.setOnClickListener {
            onCardClick(offer.link)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(offersList: List<OfferAdapterModel>) {
        _offersList.clear()
        _offersList.addAll(offersList)

        notifyDataSetChanged()
    }
}
