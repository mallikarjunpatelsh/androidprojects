package com.mallikarjun.network

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import org.json.JSONObject

interface INetworkCall {
     suspend fun fetchCustomUI (URL: String): JSONObject?
     suspend fun fetchImage(URL: String): ByteArray?
}