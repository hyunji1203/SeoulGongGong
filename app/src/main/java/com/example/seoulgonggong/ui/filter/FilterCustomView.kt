package com.example.seoulgonggong.ui.filter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.seoulgonggong.R
import com.example.seoulgonggong.databinding.CustomFilterBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class FilterCustomView(context: Context, attr: AttributeSet? = null) :
    ConstraintLayout(context, attr) {
    private val binding: CustomFilterBinding
    private val chips: MutableList<Chip> = mutableListOf()
    private var chipGroup: ChipGroup

    init {
        val inflater = LayoutInflater.from(context)
        binding = CustomFilterBinding.inflate(inflater, this, true)
        chipGroup = binding.cgFilterGroup
    }

    fun setFilterTitle(title: String) {
        binding.tvFilterTitle.text = title
    }

    fun addFilterOptionGroup(group: List<String>) {
        group.forEach {
            addFilterOption(it)
        }
    }

    private fun addFilterOption(option: String) {
        val chip = Chip(context)
        setChipSize(chip)
        chip.text = option
        chip.isCheckable = true
        setChipStyle(chip)
        chipGroup.addView(chip)
        chips.add(chip)
    }

    private fun setChipSize(chip: Chip) {
        chip.setPadding(16, 8, 16, 8)
    }

    private fun setChipStyle(chip: Chip) {
        chip.setChipBackgroundColorResource(R.color.chip_bg_color)
        chip.setTextColor(resources.getColorStateList(R.color.chip_text_color, null))
        chip.setTextAppearance(R.style.subtitle_2)
        chip.chipIcon = null
    }

    fun getSelectedOptions(): List<String> {
        val selectedOptions = mutableListOf<String>()
        chips.forEach {
            if (it.isChecked) {
                selectedOptions.add(it.text.toString())
            }
        }
        return selectedOptions
    }

    fun clearSelection() {
        chips.forEach { it.isChecked = false }
    }
}
