package com.mallikarjun.ezytapassignment

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.view.marginLeft
import androidx.core.widget.NestedScrollView
import com.mallikarjun.ezytapassignment.uimodel.UIDataModel
import com.mallikarjun.ezytapassignment.uimodel.element.ButtonUI
import com.mallikarjun.ezytapassignment.uimodel.element.UIType
import com.mallikarjun.ezytapassignment.viewgenerator.CustomGenerator

class DisplayActivity : AppCompatActivity() {
    private val TAG = "DisplayActivity"
    private lateinit var scrollView : NestedScrollView
    private lateinit var toolbar: Toolbar
    private lateinit var headingTextView: TextView
    private lateinit var logoIcon : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
        bindIds()
        this.toolbar = findViewById(R.id.included)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)

        val uiDataModel : UIDataModel? = intent?.extras?.getParcelable("uidata")


        if (uiDataModel != null) {
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.setHomeButtonEnabled(true)
            headingTextView?.text = uiDataModel.headingText
            val bitmap : Bitmap? = intent?.extras?.getParcelable("logo")
            if(bitmap  != null) {
                logoIcon.setImageBitmap(bitmap)
            }
            scrollView = findViewById(R.id.display_screen_scroll_view)
            scrollView.removeAllViews()
            findViewById<ProgressBar>(R.id.displayview_progressBar).visibility = GONE
            scrollView.addView(generateView(uiDataModel))
        }


    }

    private fun bindIds() {
        this.headingTextView = findViewById(R.id.toolbar_heading)
        this.logoIcon = findViewById(R.id.logo_icon)
    }

    fun generateView(uiDataModel: UIDataModel):View{
        val linearLayout = LinearLayout(applicationContext)
        val linearParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.layoutParams = linearParams
        linearLayout.requestLayout()
        var inputsField: CustomGenerator
        val start = System.currentTimeMillis()
        val buttons : MutableList<ButtonUI> = mutableListOf()
        for (dynamicDetailMetadata in uiDataModel.uidata) {
            val uiType:UIType = dynamicDetailMetadata?.getType();
            if(uiType != UIType.button) {
                inputsField = uiType.getCustomWidget(applicationContext)
                linearLayout.addView(inputsField.gen(dynamicDetailMetadata))
            }else{
                buttons.add(dynamicDetailMetadata as ButtonUI)
            }
        }
        val end = System.currentTimeMillis()
        Log.e(TAG, "generateElement: " + (end - start))
        generateButtons(buttons)

        return linearLayout
    }

    private fun generateButtons(buttons: List<ButtonUI>) {
        val buttonLayout : LinearLayout = findViewById(R.id.displayscreen_button_layout)
        buttonLayout.removeAllViews()
        for (buttonUi in buttons) {
            val button = Button(applicationContext)
            button.text = buttonUi.value

            buttonLayout.addView(button)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}