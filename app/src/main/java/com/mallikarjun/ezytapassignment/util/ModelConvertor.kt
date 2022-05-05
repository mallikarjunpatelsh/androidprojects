package com.mallikarjun.ezytapassignment.util

import android.util.Log
import com.google.gson.Gson
import com.mallikarjun.ezytapassignment.uimodel.UIDataModel
import com.mallikarjun.ezytapassignment.uimodel.element.ButtonUI
import com.mallikarjun.ezytapassignment.uimodel.element.EditTextUI
import com.mallikarjun.ezytapassignment.uimodel.element.IUIElements
import com.mallikarjun.ezytapassignment.uimodel.element.LabelUI
import org.json.JSONArray
import org.json.JSONObject

class ModelConvertor {

    companion object{
        @JvmStatic
        fun convertJsonToUIDataModel(jsonObject: JSONObject) : UIDataModel {
            val it = jsonObject
            var logoUrl : String = ""
            var headingText: String = ""
            var uiData : MutableList<IUIElements> = mutableListOf()
            if (it?.getString("logo-url") != null) {
                logoUrl = it.getString("logo-url").toString()
            };
            if(it?.getString("heading-text") != null) {
                headingText = it.getString("heading-text").toString()
            };
            if (it?.getJSONArray("uidata")!= null){
                var jsonArray: JSONArray = it.getJSONArray("uidata")

                for (i in 0 until jsonArray.length()) {
                    val `object`: JSONObject = jsonArray.getJSONObject(i)
                    var data: IUIElements? = null
                    if (`object`.getString("uitype").equals("label") ) {
                        data = Gson().fromJson(`object`.toString(), LabelUI::class.java)
                    } else if (`object`.getString("uitype").equals("edittext") ) {
                        data = Gson().fromJson(`object`.toString(), EditTextUI::class.java)
                    } else if (`object`.getString("uitype").equals("button") ) {
                        data = Gson().fromJson(`object`.toString(), ButtonUI::class.java)
                    }
                    if (data != null) {
                        uiData.add(data)
                    }
                }
            }

            var uiDataModel:UIDataModel = UIDataModel(logoUrl,headingText,uiData)
            Log.i("ModelConvertor", "uiData: "+uiDataModel)
            return uiDataModel
        }
    }

}