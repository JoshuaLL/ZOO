package com.ddt.zoo.ui.zone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ddt.zoo.api.ApiResult
import com.ddt.zoo.model.PlantItem
import com.ddt.zoo.repository.ApiRepository
import com.ddt.zoo.ui.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class ZoneViewModel : BaseViewModel() {

    private val apiRepository: ApiRepository by inject()
    private val _plantResult = MutableLiveData<ApiResult<ArrayList<PlantItem>?>>()
    val plantResult: LiveData<ApiResult<ArrayList<PlantItem>?>> = _plantResult

    fun getPlant() {
        viewModelScope.launch {
            apiRepository.getPlant()
                .onStart { _plantResult.value = ApiResult.loading() }
                .catch { e -> _plantResult.value = ApiResult.error(e) }
                .onCompletion { _plantResult.value = ApiResult.loaded() }
                .collect { _plantResult.value = ApiResult.success(it) }
        }
    }
}