package com.app.suitmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.suitmedia.databinding.UserItemBinding
import com.app.suitmedia.response.DataItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class UserAdapter(private val onItemClick: (String) -> Unit) :
    PagingDataAdapter<DataItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)

        if (user != null) {
            holder.bind(user)
        }
        holder.itemView.setOnClickListener {
            onItemClick(user?.firstName + " " + user?.lastName)
        }

    }

    class MyViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: DataItem) {
            with(binding) {
                tvItemName.text = user.firstName + " " + user.lastName
                tvItemDescription.text = user.email
                Glide.with(itemView.context).load(user.avatar).transform(CircleCrop())
                    .into(ivItemPhoto)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DataItem,
                newItem: DataItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}