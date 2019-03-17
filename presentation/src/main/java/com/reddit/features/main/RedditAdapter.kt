package com.reddit.features.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.reddit.R
import com.reddit.databinding.ItemPostBinding
import com.reddit.domain.model.Post

class RedditAdapter(private val viewModel: MainViewModel) :
        ListAdapter<Post, RedditAdapter.ItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(DataBindingUtil
                .inflate(LayoutInflater.from(parent.context),
                        R.layout.item_post, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(private val binding: ItemPostBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Post?) {
            binding.post = item!!
            binding.viewModel = viewModel
            binding.header.authorNameTv.text = binding.root.context.getString(
                    R.string.main_label_posted_by,
                    item.authorName,
                    item.date
            )

            if (item.imgUrl != null && item.imgUrl!!.width > 0 && item.imgUrl!!.height > 0) {
                val ratio = item.imgUrl!!.height.toFloat() / item.imgUrl!!.width
                binding.contentImg.setAspectRatioEnabled(true)
                binding.contentImg.setAspectRatio(ratio)
                binding.contentImg.visibility = View.VISIBLE
            } else {
                binding.contentImg.visibility = View.GONE
            }
            binding.imageUri = item.imgUrl?.url
            binding.executePendingBindings()
        }
    }

    companion object {
        private val DIFF_CALLBACK = object
            : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(old: Post, new: Post): Boolean {
                return old.id == new.id
            }

            override fun areContentsTheSame(old: Post, new: Post): Boolean {
                return old == new
            }
        }
    }

}