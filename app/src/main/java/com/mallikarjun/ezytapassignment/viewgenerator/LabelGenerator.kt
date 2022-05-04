package com.mallikarjun.ezytapassignment.viewgenerator

import android.content.Context
import android.graphics.Typeface
import android.text.Html
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.mallikarjun.ezytapassignment.R
import com.mallikarjun.ezytapassignment.uimodel.element.EditTextUI
import com.mallikarjun.ezytapassignment.uimodel.element.IUIElements
import com.mallikarjun.ezytapassignment.uimodel.element.LabelUI

class LabelGenerator(private val context : Context) : CustomGenerator  {
    override fun gen(uiElement: IUIElements): View {
        val labelUI : LabelUI = uiElement as LabelUI
        val textView = TextView(context)
        val textParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        textParams.setMargins(35, 20, 20, 0)
        textView.setTextColor(context.resources.getColor(R.color.labelColor))
        textView.layoutParams = textParams
        textView.text = labelUI.value

        return textView
    }

}