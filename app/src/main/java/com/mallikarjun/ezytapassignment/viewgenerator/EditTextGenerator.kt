package com.mallikarjun.ezytapassignment.viewgenerator

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import com.mallikarjun.ezytapassignment.R
import com.mallikarjun.ezytapassignment.uimodel.element.EditTextUI
import com.mallikarjun.ezytapassignment.uimodel.element.IUIElements

class EditTextGenerator(private val context : Context) : CustomGenerator {
    override fun gen(uiElement: IUIElements) : View {
        val editTextUI : EditTextUI = uiElement as EditTextUI
        val editText = EditText(context)
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(20, 5, 20, 20)
        editText.layoutParams = params
        editText.setPadding(30, 20, 20, 30)
        editText.elevation = 10f
        editText.hint = editTextUI.hint
        editText.setHintTextColor(context.resources.getColor(R.color.hintColor))
        editText.setTextColor(context.resources.getColor(R.color.textColorPrimary))
        editText.background = context.getDrawable(R.drawable.default_container_background)

        return editText
    }
}