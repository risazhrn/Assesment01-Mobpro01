package org.d3if3100.assesment1

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import org.jetbrains.annotations.Nullable

// Reference problem solve :
// https://stackoverflow.com/questions/68535808/spinner-values-dissapear-after-rotation
// library spinner bawaan android terdapat bug sehingga di buat ulang menggunakan custom view

class ExposedDropdownMenu : MaterialAutoCompleteTextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, @Nullable attributeSet: AttributeSet?) : super(
        context,
        attributeSet
    )

    constructor(context: Context, @Nullable attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    override fun getFreezesText(): Boolean {
        return false
    }
}