package com.mallikarjun.ezytapassignment.viewgenerator

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.google.android.material.button.MaterialButton
import com.mallikarjun.ezytapassignment.R
import com.mallikarjun.ezytapassignment.uimodel.element.ButtonUI
import com.mallikarjun.ezytapassignment.uimodel.element.IUIElements
import com.mallikarjun.ezytapassignment.uimodel.element.LabelUI

class ButtonGenerator(private val context : Context) : CustomGenerator  {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun gen(uiElement: IUIElements): View {
        val buttonUI: ButtonUI = uiElement as ButtonUI
        val button = MaterialButton(context)
        val textParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        textParams.setMargins(20, 20, 20, 0)
        button.layoutParams = textParams
        button.setTypeface(null, Typeface.BOLD)
        button.text = buttonUI.value

        return button
    }
}