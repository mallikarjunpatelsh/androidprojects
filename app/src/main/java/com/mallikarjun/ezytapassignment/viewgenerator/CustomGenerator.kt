package com.mallikarjun.ezytapassignment.viewgenerator

import android.view.View
import com.mallikarjun.ezytapassignment.uimodel.element.IUIElements

interface CustomGenerator {
    fun gen(uiElement : IUIElements): View
}