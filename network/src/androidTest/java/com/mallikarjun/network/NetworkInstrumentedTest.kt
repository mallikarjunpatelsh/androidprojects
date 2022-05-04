package com.mallikarjun.network

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import org.json.JSONObject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.InputStream
import java.util.*

@RunWith(AndroidJUnit4::class)
@SmallTest
internal class NetworkInstrumentedTest {
    private lateinit var testNetworkCallImpl:INetworkCall
    private lateinit var instrumentationContext: Context

    @Before
    fun createInstance(){
        testNetworkCallImpl = NetworkCallImpl()
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
    }

    @Test
    suspend fun testFetchCustomUI() {
        val testJsonRaw: InputStream = instrumentationContext.getResources().openRawResource(
        R.raw.test_json
        )
        val testJsonScanned = Scanner(testJsonRaw).useDelimiter("\\A").next()
         val expectedJson: JSONObject? =JSONObject(testJsonScanned)
        val apiCallJson : JSONObject? = testNetworkCallImpl.fetchCustomUI("https://demo.ezetap.com/mobileapps/android_assignment.json")


    }
}