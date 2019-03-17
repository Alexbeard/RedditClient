package com.reddit.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.reddit.R
import com.reddit.databinding.ItemLoadingBinding
import timber.log.Timber


class LoadingAdapter(adapter: RecyclerView.Adapter<*>, private val threshold: Int = 5) :
        RecyclerViewAdapterWrapper(adapter) {

    private var hasNextWasSet = false
    private var loading = true
    private var hasNext = true

    private var loadMoreListener: LoadMoreListener? = null
    private val loadingPosition: Int
        get() = itemCount - 1

    init {
        innerObserver = object : RecyclerView.AdapterDataObserver() {

            override fun onChanged() {
                notifyDataSetChanged()
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                notifyItemRangeChanged(positionStart, itemCount)
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                if (wrapped.itemCount == itemCount) {
                    if (hasNext) {
                        notifyItemRemoved(0)
                    }
                    notifyItemRangeInserted(positionStart, itemCount)
                    if (hasNext) {
                        notifyItemInserted(loadingPosition)
                    }
                } else {
                    notifyItemRangeInserted(positionStart, itemCount)
                }
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                notifyItemRangeRemoved(positionStart, itemCount)
            }

            override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                notifyItemMoved(fromPosition, toPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_LOADING) {
            val binding = DataBindingUtil
                    .inflate<ItemLoadingBinding>(
                            LayoutInflater.from(parent.context),
                            R.layout.item_loading, parent, false
                    )
            LoadingViewHolder(binding)
        } else {
            wrapped.onCreateViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder !is LoadingViewHolder) {
            wrapped.onBindViewHolder(holder, position)
        }
        if (!loading && hasNext && loadingPosition - threshold <= position) {
            loadMore()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoadingPosition(position)) {
            TYPE_LOADING
        } else {
            wrapped.getItemViewType(position)
        }
    }

    override fun getItemCount(): Int {
        val itemCount = wrapped.itemCount
        return if (hasNext) itemCount + 1 else itemCount
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder !is LoadingViewHolder) {
            wrapped.onViewRecycled(holder)
        }
    }

    private fun loadMore() {
        loading = true
        loadMoreListener?.onLoadMore()
    }

    private fun isLoadingPosition(position: Int): Boolean {
        return hasNext && position == loadingPosition
    }

    fun setLoadMoreListener(loadMoreListener: LoadMoreListener) {
        this.loadMoreListener = loadMoreListener
    }

    fun setLoading(loading: Boolean) {
        this.loading = loading
    }

    fun setHasNext(hasNext: Boolean) {
        if (!hasNextWasSet) {
            loading = false
        }
        hasNextWasSet = true

        try {
            if (hasNext) {
                val add = !this.hasNext
                this.hasNext = hasNext

                if (add) {
                    notifyItemInserted(loadingPosition)
                } else {
                    notifyItemChanged(loadingPosition)
                }
            } else {
                notifyItemRemoved(loadingPosition)
            }
        } catch (e: Throwable) {
            Timber.e(e)
        }
    }

    interface LoadMoreListener {
        fun onLoadMore()
    }

    private inner class LoadingViewHolder internal constructor(binding: ItemLoadingBinding) :
            RecyclerView.ViewHolder(binding.root)


    companion object {
        const val TYPE_LOADING: Int = -1000
    }
}