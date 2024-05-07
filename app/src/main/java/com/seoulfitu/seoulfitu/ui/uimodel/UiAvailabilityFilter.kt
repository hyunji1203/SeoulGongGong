package com.seoulfitu.seoulfitu.ui.uimodel

enum class UiAvailabilityFilter(val filterName: String) {
    POSSIBLE("가능"),
    IMPOSSIBLE("불가능");

    companion object {
        fun getOptions() = UiAvailabilityFilter.values().map { it.filterName }

        fun changeToAvailabilityFilter(input: String): UiAvailabilityFilter? {
            return when (input) {
                POSSIBLE.filterName -> POSSIBLE
                IMPOSSIBLE.filterName -> IMPOSSIBLE
                else -> null
            }
        }
    }
}
