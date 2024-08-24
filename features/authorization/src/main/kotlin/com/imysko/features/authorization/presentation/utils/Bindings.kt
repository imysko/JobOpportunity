package com.imysko.features.authorization.presentation.utils

import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.imysko.common.ui.R

@BindingAdapter("textIsEmpty")
fun TextInputLayout.bindTextIsEmpty(isEmpty: Boolean) {
    startIconDrawable = if (isEmpty) {
        ResourcesCompat.getDrawable(resources, R.drawable.email, null)
    } else {
        null
    }
}

@BindingAdapter("isError")
fun TextInputLayout.bindIsError(isError: Boolean) {
    if (isError) {
        boxStrokeColor = ResourcesCompat.getColor(resources, R.color.red, null)
        boxStrokeWidth = 1
    } else {
        boxStrokeColor = ResourcesCompat.getColor(resources, R.color.transparent, null)
        boxStrokeWidth = 0
    }
}
