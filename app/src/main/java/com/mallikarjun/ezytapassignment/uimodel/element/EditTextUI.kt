package com.mallikarjun.ezytapassignment.uimodel.element

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EditTextUI(@SerializedName("uitype") var uiType : UIType,
                      @SerializedName("hint") var hint : String,
                      @SerializedName("key") var key : String) : IUIElements, Parcelable {
    override fun getType(): UIType {
        return uiType
    }
}