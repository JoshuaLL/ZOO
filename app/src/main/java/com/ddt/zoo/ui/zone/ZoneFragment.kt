package com.ddt.zoo.ui.zone

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.ddt.zoo.R
import com.ddt.zoo.api.ApiResult
import com.ddt.zoo.model.ZoneItem
import kotlinx.android.synthetic.main.fragment_zone.*
import kotlinx.android.synthetic.main.fragment_zone.layout_refresh
import timber.log.Timber

class ZoneFragment : Fragment() {

    companion object {
        private const val KEY_ITEM = "KEY_ITEM"
    }

    private val viewModel: ZoneViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_zone, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<ZoneItem>(KEY_ITEM)?.let {
            zoneInfoUi(it)
        }

        PlantAdapter().let {
                plant_list.adapter =it
                subscribeUi(it)
        }

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.getPlant()
    }

    private fun zoneInfoUi(item:ZoneItem){
        toolbar.title = item.eCategory

        Glide.with(requireContext())
            .load(item.ePicUrl)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(iv_pic)

        tv_info.text = item.eInfo

        if (TextUtils.isEmpty(item.eMemo)) {
            tv_memo.visibility = View.GONE
        } else {
            tv_memo.visibility = View.INVISIBLE
            tv_memo.text = item.eMemo
        }

        tv_open_web.setOnClickListener {
            Intent().also {
                it.action = Intent.ACTION_VIEW
                it.data = Uri.parse(item.eUrl)
                startActivity(it)
            }
        }


    }

    private fun subscribeUi(plantAdapter: PlantAdapter) {
        viewModel.plantResult.observe(viewLifecycleOwner) {
            when(it){
                is ApiResult.Loading -> layout_refresh.isRefreshing = true
                is ApiResult.Loaded -> layout_refresh.isRefreshing = false
                is ApiResult.Success -> {
                    it.result?.let {
                            data -> plantAdapter.submitList(data)
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