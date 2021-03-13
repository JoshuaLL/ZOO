package com.ddt.zoo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ZoneItem(
    @SerializedName("E_Pic_URL") val ePicUrl: String = "",
    @SerializedName("E_Geo") val eGeo: String = "",
    @SerializedName("E_Info") val eInfo: String = "",
    @SerializedName("E_no") val eNo: String = "",
    @SerializedName("E_Category") val eCategory: String = "",
    @SerializedName("E_Name") val eName: String = "",
    @SerializedName("E_Memo") val eMemo: String = "",
    @SerializedName("E_URL") val eUrl: String = "",
    @SerializedName("_id") val id: Long = 0
) : Parcelable