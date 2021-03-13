package com.ddt.zoo.api

import com.google.gson.annotations.SerializedName

data class ApiItem<T>(
    @SerializedName("result")
    val result: T?
)