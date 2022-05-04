package com.mallikarjun.ezytapassignment.uimodel.element

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class ButtonUI(@SerializedName("uitype") var uiType : UIType,
               @SerializedName("value") var value : String) : IUIElements, Parcelable {
    override fun getType(): UIType {
        return uiType
    }
}