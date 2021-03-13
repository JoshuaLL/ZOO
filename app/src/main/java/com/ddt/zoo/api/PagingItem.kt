package com.ddt.zoo.api

import com.google.gson.annotations.SerializedName

data class PagingItem<T>(
    @SerializedName("limit") val limit: Int,
    @SerializedName("offset") val offset: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("sort") val sort: String,
    @SerializedName("results") val results: T?
)