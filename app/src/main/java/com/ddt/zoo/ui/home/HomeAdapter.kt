package com.ddt.zoo.ui.home

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
import com.ddt.zoo.model.ZoneItem
import kotlinx.android.synthetic.main.item_zone.view.*
import kotlinx.android.synthetic.main.item_zone.view.iv_category
import kotlinx.android.synthetic.main.item_zone.view.layout_item_category
import kotlinx.android.synthetic.main.item_zone_small.view.*

class HomeAdapter : ListAdapter<ZoneItem, RecyclerView.ViewHolder>(ZoneDiffCallback()) {

    companion object{
        const val LIST_MODE_NORMAL = 1
        const val LIST_MODE_SMALL = 2
    }
    var listMode = LIST_MODE_SMALL

    override fun getItemViewType(position: Int): Int {
        return listMode
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LIST_MODE_SMALL -> HomeViewHolder( LayoutInflater.from(parent.context)
                .inflate(R.layout.item_zone, parent, false))

            else -> HomeViewSmallHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_zone_small, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        when (holder) {
            is HomeViewHolder ->{
                Glide.with(holder.categoryImg.context)
                    .load(item.ePicUrl)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(holder.categoryImg)

                holder.categoryDesc.text = item.eInfo
                holder.categoryMemo.text = item.eMemo

                holder.categoryTitle.text = item.eName
                holder.categoryLayout.setOnClickListener {
                    holder.itemView.findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToZoneFragment(item)
                    )
                }
            }
            is HomeViewSmallHolder->{
                Glide.with(holder.categoryImg.context)
                    .load(item.ePicUrl)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(holder.categoryImg)

                holder.categoryTitle.text = item.eName
                holder.categoryLayout.setOnClickListener {
                    holder.itemView.findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToZoneFragment(item)
                    )
                }
            }
        }
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryImg: ImageView = itemView.iv_category
        var categoryTitle: TextView = itemView.tv_category_title
        var categoryDesc: TextView = itemView.tv_category_desc
        var categoryMemo: TextView = itemView.tv_category_memo
        var categoryLayout: ConstraintLayout = itemView.layout_item_category
    }

    class HomeViewSmallHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryImg: ImageView = itemView.iv_category
        var categoryTitle: TextView = itemView.category_title
        var categoryLayout: ConstraintLayout = itemView.layout_item_category
    }

    fun isEmpty(): Boolean {
        return itemCount == 0
    }
}

private class ZoneDiffCallback : DiffUtil.ItemCallback<ZoneItem>() {

    override fun areItemsTheSame(oldItem: ZoneItem, newItem: ZoneItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ZoneItem, newItem: ZoneItem): Boolean {
        return oldItem == newItem
    }
}
