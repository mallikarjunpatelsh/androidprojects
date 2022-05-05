package com.mallikarjun.ezytapassignment.uimodel

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.mallikarjun.ezytapassignment.uimodel.element.IUIElements
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class UIDataModel(@SerializedName("logo-url") var logoUrl: String?, @SerializedName("heading-text") var headingText: String?, var uidata: @RawValue List<IUIElements>) : Parcelable{



}