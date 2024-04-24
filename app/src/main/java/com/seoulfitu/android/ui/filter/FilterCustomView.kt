package com.seoulfitu.android.ui.filter

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.shape.CornerFamily.ROUNDED
import com.google.android.material.shape.ShapeAppearanceModel
import com.seoulfitu.android.R
import com.seoulfitu.android.databinding.CustomFilterBinding

class FilterCustomView(context: Context, attr: AttributeSet? = null) :
    ConstraintLayout(context, attr) {
    private val binding: CustomFilterBinding
    private val chips: MutableList<Chip> = mutableListOf()
    private var chipGroup: ChipGroup

    init {
        val inflater = LayoutInflater.from(context)
        binding = CustomFilterBinding.inflate(inflater, this, true)
        chipGroup = binding.cgFilterGroup

        val typedArray = context.obtainStyledAttributes(attr, R.styleable.FilterCustomView)
        setSingleChipOption(typedArray)
    }

    private fun setSingleChipOption(filterSingleOption: TypedArray) {
        chipGroup.isSingleSelection = filterSingleOption.getBoolean(
            R.styleable.FilterCustomView_single_selection,
            false,
        )
    }

    fun setFilterTitle(title: String) {
        binding.tvFilterTitle.text = title
    }

    fun addFilterOptionGroup(
        group: List<String>,
        selected: List<String>,
    ) {
        group.forEach {
            addFilterOption(it, selected)
        }
    }

    private fun addFilterOption(
        option: String,
        selected: List<String>,
    ) {
        val chip = Chip(context)
        setChipSize(chip)
        chip.text = option
        chip.isCheckable = true
        if (selected.contains(option)) chip.isChecked = true
        setChipStyle(chip)
        chipGroup.addView(chip)
        chips.add(chip)
    }

    private fun setChipSize(chip: Chip) {
        chip.setPadding(16, 8, 16, 8)
        val layoutParams =
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
        chip.layoutParams = layoutParams
    }

    private fun setChipStyle(chip: Chip) {
        chip.setChipBackgroundColorResource(R.color.chip_bg_color)
        chip.setTextColor(resources.getColorStateList(R.color.chip_text_color, null))
        chip.setChipStrokeColorResource(R.color.main_teal)
        chip.chipStrokeWidth = 5F
        chip.shapeAppearanceModel =
            ShapeAppearanceModel.builder()
                .setAllCorners(ROUNDED, 50F)
                .build()
        chip.setTextAppearance(R.style.subtitle_2)
        chip.chipIcon = null
    }

    fun getSelectedOptions(): List<String> {
        return chips.filter { it.isChecked }.map { it.text.toString() }
    }

    fun getSelectedOption(): String {
        val selectedOption = getSelectedOptions()
        return if (selectedOption.isEmpty()) ""
        else selectedOption.first()
    }

    fun clearSelection() {
        chips.forEach { it.isChecked = false }
    }
}
