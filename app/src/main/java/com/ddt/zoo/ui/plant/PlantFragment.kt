package com.ddt.zoo.ui.plant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.ddt.zoo.R
import com.ddt.zoo.model.PlantItem
import kotlinx.android.synthetic.main.fragment_plant.*

class PlantFragment : Fragment() {

    val plantViewModel: PlantViewModel by viewModels()

    companion object {
        private const val KEY_ITEM = "KEY_ITEM"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_plant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<PlantItem>(KEY_ITEM)?.let {
            plantInfoUi(it)
        }

        toolbar.title = resources.getString(R.string.plants_detail)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun plantInfoUi(plantItem:PlantItem){
        Glide.with(requireContext())
            .load(plantItem.fPic01Url)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(plant_pic)

        cn_name.text = plantItem.fNameCh
        en_name.text = plantItem.fNameEn
        alias_names.text = plantItem.fAlsoKnown
        brief.text = plantItem.fBrief
        recognition_methods.text = plantItem.fFeature
        function.text = plantItem.fFunctionApplication
        last_update_time.text = plantItem.fUpdate

    }
}