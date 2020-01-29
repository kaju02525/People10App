package com.popwoot.mvvm

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.popwoot.model.ApiResponse
import com.popwoot.network.ApiStatus.isCheckAPIStatus
import com.popwoot.network.RestClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.android.Main
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    val requestData = MutableLiveData<ApiResponse>()
    val errorMess = MutableLiveData<String>()


    @SuppressLint("CheckResult")
    fun getApiCall() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                RestClient.webServices().getAsync().await().let {
                    if (it.isSuccessful)
                       requestData.value = it.body()!!
                    else
                        errorMess.value=isCheckAPIStatus(it.code(),it.errorBody())
                }
            }
            catch (e:Exception){
                e.printStackTrace()
                errorMess.value="Internet not available!!"
            }
        }
    }


}