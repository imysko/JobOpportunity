package com.imysko.features.searchVacancy.presentation.utils

import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.imysko.common.ui.R
import com.imysko.data.offers.domain.entities.OfferId

@BindingAdapter("offer_id")
fun ImageView.bindOfferId(offerId: OfferId?) {
    when (offerId) {
        OfferId.NEAR_VACANCIES -> {
            setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.geo, null))
        }

        OfferId.LEVEL_UP_RESUME -> {
            setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.star, null))
        }

        OfferId.TEMPORARY_JOB -> {
            setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.vacancy, null))
        }

        null -> Unit
    }
}

@BindingAdapter("offer_id")
fun FrameLayout.bindOfferId(offerId: OfferId?) {
    background = when (offerId) {
        OfferId.NEAR_VACANCIES -> ResourcesCompat.getDrawable(
            resources,
            com.imysko.features.searchVacancy.R.drawable.offer_icon_background_blue,
            null
        )

        OfferId.LEVEL_UP_RESUME, OfferId.TEMPORARY_JOB -> ResourcesCompat.getDrawable(
            resources,
            com.imysko.features.searchVacancy.R.drawable.offer_icon_background_green,
            null
        )

        null -> null
    }
}
