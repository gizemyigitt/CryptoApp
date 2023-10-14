package com.example.cryptoapp.network

import com.example.cryptoapp.model.detail.DetailResponse
import com.example.cryptoapp.model.home.CryptoResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiFactory {

    @GET(value = "v1/cryptocurrency/listings/latest")
    suspend fun getData(
        @Header(value = "X-CMC_PRO_API_KEY") apiKey: String,
        @Query(value = "limit") limit: String
    ): CryptoResponse

    @GET(value = "v2/cryptocurrency/info")
    suspend fun getDetail(
        @Header(value = "X-CMC_PRO_API_KEY") apiKey: String,
        @Query(value = "symbol") symbol: String
    ):DetailResponse
}