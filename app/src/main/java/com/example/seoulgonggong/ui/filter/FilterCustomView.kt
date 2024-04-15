package com.example.seoulgonggong.ui.filter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.seoulgonggong.R
import com.example.seoulgonggong.databinding.CustomFilterBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
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

    // 필터 옵션 추가
    fun addFilterOption(option: String) {
        val chip = Chip(context)
        chip.text = option
        chip.isCheckable = true
        setChipStyle(chip)
        chipGroup.addView(chip)
        chips.add(chip)
    }

    private fun setChipStyle(chip: Chip) {
        chip.setChipDrawable(
            ChipDrawable.createFromAttributes(context, null, 0, R.style.CustomChipChoice),
        )
        chip.setTextAppearance(R.style.subtitle_1)
    }

    // 선택된 옵션 확인
    fun getSelectedOptions(): List<String> {
        val selectedOptions = mutableListOf<String>()
        chips.forEach {
            if (it.isChecked) {
                selectedOptions.add(it.text.toString())
            }
        }
        return selectedOptions
    }

    // 모든 옵션 해제
    fun clearSelection() {
        chips.forEach { it.isChecked = false }
    }
}
