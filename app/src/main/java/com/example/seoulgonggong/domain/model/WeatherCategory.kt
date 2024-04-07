package com.example.seoulgonggong.domain.model

enum class WeatherCategory {
    POP,
    PTY,
    SKY,
    TMP,
    OTHER, ;

    companion object {
        fun findByName(category: String): WeatherCategory {
            return values().find { it.name == category } ?: OTHER
        }
    }
}
