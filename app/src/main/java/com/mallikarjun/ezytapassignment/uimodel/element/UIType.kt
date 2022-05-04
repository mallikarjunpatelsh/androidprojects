package com.mallikarjun.ezytapassignment.uimodel.element

import android.content.Context
import com.mallikarjun.ezytapassignment.viewgenerator.ButtonGenerator
import com.mallikarjun.ezytapassignment.viewgenerator.CustomGenerator
import com.mallikarjun.ezytapassignment.viewgenerator.EditTextGenerator
import com.mallikarjun.ezytapassignment.viewgenerator.LabelGenerator

enum class UIType(s: String) {
    edittext("edittext"){
        override fun getCustomWidget(context: Context): CustomGenerator {
            return EditTextGenerator(context)
        }
                        },
    button("button"){
        override fun getCustomWidget(context: Context): CustomGenerator {
            return ButtonGenerator(context)
        }

    },
    label("label") {
        override fun getCustomWidget(context: Context): CustomGenerator {
            return LabelGenerator(context)
        }
    };

    public abstract fun getCustomWidget( context : Context) : CustomGenerator;
}