package com.example.seoulgonggong.domain.model

data class SportsFacilityInfo(
    val idx: Double,
    val borough: String,
    val facilityName: String,
    val facilityCategory: String,
    val postNumber: String,
    val address: String,
    val addressDetail: String,
    val facilitySize: String,
    val operatingOrganization: String,
    val phoneNumber: String,
    val operatingTimeWeekday: String,
    val operatingTimeWeekend: String,
    val operatingTimeHoliday: String,
    val canRental: String,
    val money: String,
    val parkingInfo: String,
    val homepageUrl: String,
    val type: String,
    val isOperating: String,
    val convenience: String,
    val note: String,
) {
    companion object {
        fun getPhoneNumber(phoneNumber: String): String? {
            if (phoneNumber.length < 3) return null
            val phone = phoneNumber.filter { Character.isDigit(it) }
            return "tel:$phone"
        }
    }
}
