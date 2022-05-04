package com.mallikarjun.ezytapassignment.uimodel.element

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LabelUI(@SerializedName("uitype") var uiType : UIType,
                   @SerializedName("value") var value : String,
                   @SerializedName("key") var key : String) : IUIElements, Parcelable {
    override fun getType(): UIType {
        return uiType
    }
}