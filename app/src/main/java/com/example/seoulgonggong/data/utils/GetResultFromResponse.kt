package com.example.seoulgonggong.data.utils

import com.example.seoulgonggong.data.ERROR_MESSAGE_FAIL_RESULT
import com.example.seoulgonggong.data.ERROR_MESSAGE_NO_BODY
import retrofit2.Response


/*
 * T : 도메인 모델
 * V : 데이터 모델
 */

fun <T, V : DomainConvertible<T>> getResult(response: Response<V>): Result<T> {
    if (response.isSuccessful) {
        val body: V = response.body() ?: return Result.failure(IllegalStateException(ERROR_MESSAGE_NO_BODY))
        return Result.success(body.toDomain())
    } else {
        return Result.failure(IllegalStateException(ERROR_MESSAGE_FAIL_RESULT))
    }
}