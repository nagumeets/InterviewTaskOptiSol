package com.example.interviewtask.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewtask.databinding.ItemFeedsBinding
import com.example.interviewtask.repository.db.Note
import com.example.interviewtask.ui.visible

class FeedAdapter(private val context: Context, private var userList: ArrayList<Note>,private var onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<FeedAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeedAdapter.UserViewHolder {
        return UserViewHolder(
            ItemFeedsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = userList[position]
        Log.e("dataaa",item.toString())
        item?.let { holder.bindFeeds(it)
            holder.itemView.setOnClickListener {
               onItemClickListener.onItemClick(item)
            }
        }

    }

    inner class UserViewHolder(private var binding: ItemFeedsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindFeeds(item: Note) = with(binding) {
            nameTxt.text = item.name
            dateTimeTxt.text=item.DateTime
            if (item.isCheck)
                liveTxt.visible(true)
            else
                liveTxt.visible(false)
            
        }
    }
}