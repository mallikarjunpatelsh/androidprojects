package com.mallikarjun.ezytapassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mallikarjun.network.INetworkCall
import com.mallikarjun.network.NetworkCallImpl
import org.json.JSONObject

class ApiViewModel : ViewModel() {
    val TAG : String = "ApiViewModel"
    private var jsonObject: MutableLiveData<JSONObject> = MutableLiveData()
    private var bytes: MutableLiveData<ByteArray> = MutableLiveData()
    val iNetworkCall: INetworkCall = NetworkCallImpl()

    suspend fun doApiCallForUI(url : String) {
        this.jsonObject.postValue(iNetworkCall.fetchCustomUI(url))
    }

     fun getJsonObjectLiveData():LiveData<JSONObject>{
        return jsonObject;
    }

    suspend fun doApiCallForImage(url: String?){
        if (url != null) {
            this.bytes.postValue(iNetworkCall.fetchImage(url))
        }
    }

     fun getBytes():LiveData<ByteArray>{
        return bytes;
    }
}