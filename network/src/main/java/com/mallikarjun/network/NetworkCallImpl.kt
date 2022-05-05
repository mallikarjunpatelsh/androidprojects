package com.mallikarjun.network


import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.CountDownLatch

class NetworkCallImpl : INetworkCall {
    val TAG : String = "NetworkCallImpl"

    override suspend fun fetchCustomUI(URL: String): JSONObject? {
        val client : OkHttpClient = OkHttpClient()
        var countDownLatch:CountDownLatch = CountDownLatch(1)
        var json: JSONObject? = null

        val request: Request = Request.Builder()
            .url(URL)
            .build()
        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, "onFailure: failed to fetch URL data")
                json = null
                countDownLatch.countDown()
            }

            override fun onResponse(call: Call, response: Response) {
                json = JSONObject(response.body?.string())
                Log.i(TAG, "onResponse: "+json)
                countDownLatch.countDown()
            }
        })
        countDownLatch.await()
        return json
    }

    override suspend fun fetchImage(URL: String) : ByteArray? {
        val client : OkHttpClient = OkHttpClient()
        var byteArray:ByteArray? = null
        var countDownLatch:CountDownLatch = CountDownLatch(1)
        val request: Request = Request.Builder()
            .url(URL)
            .build()
        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, "onFailure: failed to fetch URL data")
                byteArray = null
                countDownLatch.countDown()
            }
            override fun onResponse(call: Call, response: Response) {
                 byteArray = response.body?.bytes()
                Log.i(TAG, "onResponse:image  "+byteArray)
                countDownLatch.countDown()
            }
        })
        countDownLatch.await()
        return byteArray
    }

}