package com.ddt.zoo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ddt.zoo.api.ApiResult
import com.ddt.zoo.model.ZoneItem
import com.ddt.zoo.repository.ApiRepository
import com.ddt.zoo.ui.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import org.koin.core.component.inject


class HomeViewModel: BaseViewModel() {

    private val apiRepository: ApiRepository by inject()

    private val _zonesResult = MutableLiveData<ApiResult<ArrayList<ZoneItem>?>>()
    val zonesResult: LiveData<ApiResult<ArrayList<ZoneItem>?>> = _zonesResult

    fun getZones() {
        viewModelScope.launch {
            apiRepository.getZoo()
                .onStart { _zonesResult.value = ApiResult.loading() }
                .catch { e -> _zonesResult.value = ApiResult.error(e) }
                .onCompletion { _zonesResult.value = ApiResult.loaded() }
                .collect {
                    _zonesResult.value = ApiResult.success(it)
                }
        }
    }
}