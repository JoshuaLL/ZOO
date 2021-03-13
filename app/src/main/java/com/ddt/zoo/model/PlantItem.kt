package com.ddt.zoo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlantItem(
    @SerializedName("F_Name_Latin") val fNameLatin: String = "",
    @SerializedName("F_pdf02_ALT") val fPdf02Alt: String = "",
    @SerializedName("F_Location") val fLocation: String = "",
    @SerializedName("F_pdf01_ALT") val fPdf01ALT: String = "",
    @SerializedName("F_Summary") val fSummary: String = "",
    @SerializedName("F_Pic01_URL") val fPic01Url: String = "",
    @SerializedName("F_pdf02_URL") val fPdf02Url: String = "",
    @SerializedName("F_Pic02_URL") val fPic02Url: String = "",
    @SerializedName("\uFEFFF_Name_Ch") val fNameCh: String = "", //Character correction
    @SerializedName("F_Keywords") val fKeywords: String = "",
    @SerializedName("F_Code") val fCode: String = "",
    @SerializedName("F_Geo") val fGeo: String = "",
    @SerializedName("F_Pic03_URL") val fPic03Url: String = "",
    @SerializedName("F_Voice01_ALT") val fVoice01Alt: String = "",
    @SerializedName("F_AlsoKnown") val fAlsoKnown: String = "",
    @SerializedName("F_Voice02_ALT") val fVoice02Alt: String = "",
    @SerializedName("F_Pic04_ALT") val fPic04Alt: String = "",
    @SerializedName("F_Name_En") val fNameEn: String = "",
    @SerializedName("F_Brief") val fBrief: String = "",
    @SerializedName("F_Pic04_URL") val fPic04Url: String = "",
    @SerializedName("F_Voice01_URL") val fVoice01Url: String = "",
    @SerializedName("F_Feature") val fFeature: String = "",
    @SerializedName("F_Pic02_ALT") val fPic02Alt: String = "",
    @SerializedName("F_Family") val fFamily: String = "",
    @SerializedName("F_Voice03_ALT") val fVoice03Alt: String = "",
    @SerializedName("F_Voice02_URL") val fVoice02Url: String = "",
    @SerializedName("F_Pic03_ALT") val fPic03Alt: String = "",
    @SerializedName("F_Pic01_ALT") val fPic01Alt: String = "",
    @SerializedName("F_CID") val fCid: String = "",
    @SerializedName("F_pdf01_URL") val fPdf01Url: String = "",
    @SerializedName("F_Vedio_URL") val fVideoUrl: String = "",
    @SerializedName("F_Genus") val fGenus: String = "",
    @SerializedName("F_Function＆Application") val fFunctionApplication: String = "",
    @SerializedName("F_Voice03_URL") val fVoice03Url: String = "",
    @SerializedName("F_Update") val fUpdate: String = "",
    @SerializedName("_id") val id: Long = 0,
) : Parcelable