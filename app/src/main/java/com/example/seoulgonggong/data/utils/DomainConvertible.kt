package com.example.seoulgonggong.data.utils

interface DomainConvertible<T> {
    fun toDomain(): T
}