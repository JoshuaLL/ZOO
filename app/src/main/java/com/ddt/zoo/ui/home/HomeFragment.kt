package com.ddt.zoo.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ddt.zoo.R
import com.ddt.zoo.api.ApiResult
import com.ddt.zoo.ui.home.HomeAdapter.Companion.LIST_MODE_NORMAL
import com.ddt.zoo.ui.home.HomeAdapter.Companion.LIST_MODE_SMALL
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home_list.*
import kotlinx.android.synthetic.main.fragment_home_list.toolbar
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
        requireActivity().toolbar.visibility = VideoView.GONE
        
        val homeAdapter = HomeAdapter().also {
            val layoutManager= GridLayoutManager(activity, 2)
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return it.listMode
                }
            }
            zone_list.layoutManager = layoutManager
            zone_list.adapter =it
            subscribeUi(it)
        }

        requireActivity().onBackPressedDispatcher.addCallback(
                owner = viewLifecycleOwner,
                onBackPressed = {
                    requireActivity().finish()
                }
        )

        toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.option_list_mode ->{
                    if(homeAdapter.listMode == LIST_MODE_NORMAL){
                        homeAdapter.listMode = LIST_MODE_SMALL
                        it.setIcon(R.drawable.btn_nav_listmode_small_normal)
                    }else{
                        homeAdapter.listMode = LIST_MODE_NORMAL
                        it.setIcon(R.drawable.btn_nav_listmode_hr_normal)
                    }
                    homeAdapter.notifyDataSetChanged()
                }

                R.id.action_settings->{
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSettingsFragment())
                }
            }
            true
        }
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