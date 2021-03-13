package com.ddt.zoo.api

import com.ddt.zoo.model.PlantItem
import com.ddt.zoo.model.ZoneItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    suspend fun getZoo(): Response<ApiItem<PagingItem<ArrayList<ZoneItem>>>>

    @GET("f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire")
    suspend fun getArboretum(): Response<ApiItem<PagingItem<ArrayList<PlantItem>>>>
}