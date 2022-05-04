package com.mallikarjun.ezytapassignment

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.mallikarjun.ezytapassignment.uimodel.UIDataModel
import com.mallikarjun.ezytapassignment.util.ModelConvertor
import com.mallikarjun.ezytapassignment.viewmodel.ApiViewModel
import com.mallikarjun.network.INetworkCall
import com.mallikarjun.network.NetworkCallImpl
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.ByteArrayInputStream
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    val TAG: String = "MainActivity"
    lateinit var viewModel: ApiViewModel
    lateinit var uiDataModel: UIDataModel
    private lateinit var  jsonObject : JSONObject
    private lateinit var progessBar:ProgressBar
    private lateinit var loadingText:TextView
    private lateinit var navigationButton: Button
    private lateinit var toolbar:Toolbar
    private lateinit var headingTextView: TextView
    private lateinit var logoIcon : ImageView
    private lateinit var  bitmap : Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindIds()
        toolbar = findViewById(R.id.included)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)
        viewModel = ViewModelProvider(this).get(ApiViewModel::class.java)

        lifecycleScope.launch {
            fetchJson()
        }

    }

    private fun bindIds() {
        this.progessBar = findViewById(R.id.progress_bar)
        this.loadingText = findViewById(R.id.loading_text)
        this.navigationButton = findViewById(R.id.move_to_next_activity)
        this.headingTextView = findViewById(R.id.toolbar_heading)
        this.logoIcon = findViewById(R.id.logo_icon)
        this.navigationButton.setOnClickListener{
           val intent = Intent(this , DisplayActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("uidata",uiDataModel)
            if(bitmap != null) {
                bundle.putParcelable("logo", bitmap)
            }
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private suspend fun fetchJson() {
        viewModel.doApiCallForUI("https://demo.ezetap.com/mobileapps/android_assignment.json")
        viewModel.getJsonObjectLiveData().observe(this, Observer {
            if (it != null){
                uiDataModel = ModelConvertor.convertJsonToUIDataModel(it)
                headingTextView?.text = uiDataModel.headingText
                lifecycleScope.launch {
                   fetchImage()
                }
                this.navigationButton.visibility = VISIBLE
            }else{
                Log.i(TAG, "fetchJson: null")
                this.progessBar.visibility = GONE
                this.loadingText.text = "Failed to fetch JSON data"
            }
        })
    }

    private suspend fun fetchImage() {
        viewModel.doApiCallForImage(uiDataModel?.logoUrl)
        viewModel.getBytes().observe(this@MainActivity, Observer {
            if (it != null) {
                val inputStream: InputStream = ByteArrayInputStream(it)
                bitmap  = BitmapFactory.decodeStream(
                    inputStream
                )

                logoIcon.setImageBitmap(bitmap)

            }else{
                Log.i(TAG, "fetchJson: null")
                this.loadingText.text="Failed to load Image"
            }
            this.progessBar.visibility = GONE
            this.loadingText.visibility = GONE
        })
    }
}






