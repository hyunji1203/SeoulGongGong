package com.example.seoulgonggong.data.repository

import android.accounts.NetworkErrorException
import android.util.Log
import com.example.seoulgonggong.data.model.mapper.toDomain
import com.example.seoulgonggong.data.service.DustService
import com.example.seoulgonggong.domain.model.Dust
import com.example.seoulgonggong.domain.repository.DustRepository

class DefaultDustRepository(
    private val dustService: DustService,
) : DustRepository {
    override suspend fun getDust(msrsteNm: String): Dust {
        val response = dustService.getDust(msrsteNm)
        if (response.isSuccessful) {
            return response.body()!!.toDomain()
        } else {
            Log.d("test", "else 문")
            throw NetworkErrorException("네트워크 오류")
        }
    }
}
