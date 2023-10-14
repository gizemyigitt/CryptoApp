package com.example.cryptoapp.ui.home

import androidx.viewpager2.widget.ViewPager2.OffscreenPageLimit
import com.example.cryptoapp.base.BaseRepository
import com.example.cryptoapp.network.ApiFactory
import java.nio.channels.spi.AbstractSelectionKey
import javax.inject.Inject

class HomeRepository @Inject constructor(private  val apiFactory: ApiFactory) : BaseRepository(){
    //asenkron çalıştığı için suspend fun
    suspend fun getData(
        apiKey: String,
        limit: String
    ) = safeApiRequest{
        apiFactory.getData(apiKey,limit)
    }
}