package com.example.seoulgonggong.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException

class ResponseFilterInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // 기존의 요청을 확인합니다.
        val request = chain.request()

        try {
            val response = chain.proceed(request)
            if (!response.isSuccessful) {
                throw IOException("Network not successful")
            }

            val jsonString = response.body!!.string()
            val bodyFiltered = filteringData(jsonString)

            return response.newBuilder()
                .message(response.message)
                .body(bodyFiltered.toResponseBody())
                .build()
        } catch (ie: IOException) {
            throw ie
        }
    }

    private fun filteringData(input: String): String {
        // 데이터를 필터링하여 반환합니다.
        // 1. 입력된 JsonString을 다시 객체화 시키는 과정
        val typeToken = object : TypeToken<retrofit2.Response<*>>() {}.type
        val result: retrofit2.Response<*> = Gson().fromJson(input, typeToken)

        // 2. 필요한 부분만 꺼내서 다시 JsonString으로 만드는 과정
        val body = result.body()
        val contents = Gson().toJson(body) // Items만 꺼낸다.

        return contents
    }
}
