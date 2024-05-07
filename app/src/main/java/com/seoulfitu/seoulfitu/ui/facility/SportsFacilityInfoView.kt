package com.seoulfitu.seoulfitu.ui.facility

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.seoulfitu.seoulfitu.databinding.ItemFacilityBinding
import com.seoulfitu.seoulfitu.ui.uimodel.UiSportsFacility

class SportsFacilityInfoView(context: Context, attr: AttributeSet? = null) :
    ConstraintLayout(context, attr) {

    private val binding: ItemFacilityBinding

    init {
        val inflater = LayoutInflater.from(context)
        binding = ItemFacilityBinding.inflate(inflater, this, true)
    }

    fun setInfoItem(item: UiSportsFacility) {
        this.visibility = View.VISIBLE
        binding.facilityItem = item
    }

    fun setClickEvent(openFacilityEvent: (UiSportsFacility) -> Unit) {
        binding.openFacilityEvent = openFacilityEvent
    }
}
