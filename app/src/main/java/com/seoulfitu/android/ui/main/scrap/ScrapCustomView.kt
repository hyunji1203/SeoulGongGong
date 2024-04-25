package com.seoulfitu.android.ui.main.scrap

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ListAdapter
import com.seoulfitu.android.databinding.CustomScrapBinding
import com.seoulfitu.android.databinding.CustomScrapEmptyBinding

class ScrapCustomView(context: Context, attr: AttributeSet? = null) :
    ConstraintLayout(context, attr) {
    private lateinit var scrapBinding: CustomScrapBinding
    private lateinit var emptyBinding: CustomScrapEmptyBinding

    fun setAdapter(isEmpty: Boolean, adapter: ListAdapter<*, *>) {
        removeAllViews()
        if (isEmpty) {
            initEmpty()
        } else {
            initScrap()
            scrapBinding.rvScrapList.adapter = adapter
        }
    }

    private fun initScrap() {
        val inflater = LayoutInflater.from(context)
        scrapBinding = CustomScrapBinding.inflate(inflater, this, true)
    }

    private fun initEmpty() {
        val inflater = LayoutInflater.from(context)
        emptyBinding = CustomScrapEmptyBinding.inflate(inflater, this, true)
    }
}
