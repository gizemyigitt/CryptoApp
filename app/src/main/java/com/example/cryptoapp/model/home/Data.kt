package com.example.cryptoapp.model.home



data class Data(

    val date_added: String?,
    val id: Int?,
    val last_updated: String?,
    val name: String?,
    val platform: Platform?,
    val quote: Quote?,
    val self_reported_circulating_supply: Long?,
    val self_reported_market_cap: Double?,
    val slug: String?,
    val symbol: String?,
    val tags: List<String?>?,
    //val tvl_ratio: Any?
)