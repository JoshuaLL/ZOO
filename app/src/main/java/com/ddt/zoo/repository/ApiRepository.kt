package com.ddt.zoo.repository

import com.ddt.zoo.api.ApiService
import com.ddt.zoo.model.ZoneItem
import com.ddt.zoo.model.PlantItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.dsl.module
import retrofit2.HttpException

class ApiRepository(
    private val apiService: ApiService
) {

    suspend fun getZoo(): Flow<ArrayList<ZoneItem>?> {
        return flow {
            val result = apiService.getZoo()
            if (!result.isSuccessful) throw HttpException(result)
            val data = result.body()?.result?.results
            emit(data)
        }
    }

    suspend fun getPlant(): Flow<ArrayList<PlantItem>?> {
        return flow {
            val result = apiService.getArboretum()
            if (!result.isSuccessful) throw HttpException(result)
            val data = result.body()?.result?.results
            emit(data)
        }
    }
}