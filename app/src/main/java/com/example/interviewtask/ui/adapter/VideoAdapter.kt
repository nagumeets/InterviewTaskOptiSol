package com.example.interviewtask.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewtask.R
import com.example.interviewtask.data.models.FeatureX
import com.example.interviewtask.databinding.ItemVideosBinding
import com.example.interviewtask.ui.loadImage


class VideoAdapter :
    PagingDataAdapter<FeatureX, VideoAdapter.FeatureViewHolder>(FeatureComparator) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeatureViewHolder {
        return FeatureViewHolder(
            ItemVideosBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindVideo(it) }
    }

    inner class FeatureViewHolder(private val binding: ItemVideosBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindVideo(item: FeatureX) = with(binding) {

            val des = item.properties.description;
            if (des.contains("<img")) {
                val start: String =
                    item.properties.description.substring(item.properties.description.indexOf("'") + 1)
                val src = start.substring(0, start.indexOf("'"))

                featureImage.loadImage(src, true)
            } else {
                featureImage.loadImage("", false)
            }
            featureTitle.text = item.properties.title
        }
    }

    object FeatureComparator : DiffUtil.ItemCallback<FeatureX>() {
        override fun areItemsTheSame(oldItem: FeatureX, newItem: FeatureX): Boolean {
            return oldItem.type == newItem.type
        }

        override fun areContentsTheSame(oldItem: FeatureX, newItem: FeatureX): Boolean {
            return oldItem == newItem
        }
    }
}