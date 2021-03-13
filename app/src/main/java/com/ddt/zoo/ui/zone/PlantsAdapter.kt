package com.ddt.zoo.ui.zone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.ddt.zoo.R
import com.ddt.zoo.model.PlantItem
import kotlinx.android.synthetic.main.item_plant.view.*

class PlantAdapter : ListAdapter<PlantItem, RecyclerView.ViewHolder>(PlantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val mView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_plant, parent, false)
        return PlantViewHolder(mView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as PlantViewHolder

        val item = getItem(position)

        Glide.with(holder.plantImg.context)
            .load(item.fPic01Url)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(holder.plantImg)

        holder.plantTitle.text = item.fNameCh
        holder.plantDesc.text = item.fAlsoKnown

        holder.plantLayout.setOnClickListener {
            holder.navigateToPlant(item)
        }
    }

    class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var plantImg: ImageView = itemView.iv_plant
        var plantTitle: TextView = itemView.tv_plant_title
        var plantDesc: TextView = itemView.tv_plant_desc
        var plantLayout: ConstraintLayout = itemView.layout_item_plant

        fun navigateToPlant(
            plantItem: PlantItem
        ) {
            val direction =
                ZoneFragmentDirections.actionZoneFragmentToPlantFragment(
                    plantItem
                )
            itemView.findNavController().navigate(direction)
        }
    }

    fun isEmpty(): Boolean {
        return itemCount == 0
    }
}

private class PlantDiffCallback : DiffUtil.ItemCallback<PlantItem>() {

    override fun areItemsTheSame(oldItem: PlantItem, newItem: PlantItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PlantItem, newItem: PlantItem): Boolean {
        return oldItem == newItem
    }
}
