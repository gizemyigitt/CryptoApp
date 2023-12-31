package com.example.cryptoapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.model.home.CryptoResponse
import com.example.cryptoapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private  val repository: HomeRepository) : ViewModel() {
    //burada 3 live data oluşturduk

    //birincisi başarılı bi şekilde gelirse bu veriyi ui da işleyeceğimiz
    //loading için bi tane
    //hata için de bi tane
    val cryptoResponse: MutableLiveData<CryptoResponse?> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val onError: MutableLiveData<String?> = MutableLiveData()
//fonksiyon asenkron old için sadece view model scope ile çalışılabilir

    fun getData(
        apiKey: String,
        limit: String
    )= viewModelScope.launch {
        isLoading.value = true
        val request = repository.getData(apiKey, limit)
        when(request){
            is NetworkResult.Success ->{
                cryptoResponse.value=request.data
                isLoading.value=false
            }
            is NetworkResult.Error -> {
                onError.value= request.message
                isLoading.value=false
            }
        }

    }
}