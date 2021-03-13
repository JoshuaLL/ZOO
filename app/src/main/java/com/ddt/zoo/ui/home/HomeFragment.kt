package com.ddt.zoo.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ddt.zoo.R
import com.ddt.zoo.api.ApiResult
import kotlinx.android.synthetic.main.fragment_home_list.*
import timber.log.Timber

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        HomeAdapter().let {
            zone_list.adapter =it
            subscribeUi(it)
        }

        requireActivity().onBackPressedDispatcher.addCallback(
                owner = viewLifecycleOwner,
                onBackPressed = {
                    requireActivity().finish()
                }
        )
        viewModel.getZones()
    }

    private fun subscribeUi(homeAdapter:HomeAdapter) {
        viewModel.zonesResult.observe(viewLifecycleOwner) {
            when(it){
                is ApiResult.Loading -> layout_refresh.isRefreshing = true
                is ApiResult.Loaded -> layout_refresh.isRefreshing = false
                is ApiResult.Success -> {

                    it.result?.let { data ->
                        Timber.i("zonesResult data: $data")
                        homeAdapter.submitList(data)
                    }
                    tv_data_empty.visibility = when {
                        homeAdapter.isEmpty() -> View.VISIBLE
                        else -> View.GONE
                    }
                }
                is Error -> onApiError(it.cause)
                else -> {}
            }
        }
    }

    private fun onApiError(throwable: Throwable?) {
        Timber.e("onApiError: $throwable")
    }

}