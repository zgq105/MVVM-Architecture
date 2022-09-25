package com.guoqiang.base.common.paging

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.guoqiang.base.utils.LogUtil
import com.guoqiang.base.utils.extensions.gone
import com.guoqiang.base.utils.extensions.visible
import com.guoqiang.business.common.R
import com.guoqiang.business.common.databinding.LayoutPagingBottomBinding


/**
 * author: zgq
 * date: 2022/7/13 10:01 下午
 * destcription:
 */
class BasePagingFooterAdapter() :
    LoadStateAdapter<BasePagingFooterAdapter.PagingFooterViewHolder>() {

    companion object {
        private const val TAG = "BasePagingFooterAdapter"
    }

    private var onItemClick: (() -> Unit)? = null

    fun setOnItemClick(onItemClick: () -> Unit) {
        this.onItemClick = onItemClick
    }

    override fun onBindViewHolder(holder: PagingFooterViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PagingFooterViewHolder {
        val pagingFooterViewHolder = PagingFooterViewHolder(parent)
        pagingFooterViewHolder.itemView.setOnClickListener {
            onItemClick?.invoke()
        }
        return pagingFooterViewHolder
    }


    class PagingFooterViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_paging_bottom, parent, false)
        ) {

        private val binding = LayoutPagingBottomBinding.bind(itemView)

        fun bind(loadState: LoadState) {
            LogUtil.debug("zgq", "LoadState:$loadState")
            when (loadState) {
                is LoadState.Error -> {
                    binding.tvErrorTip.visible()
                    binding.llLoading.gone()
                }
                is LoadState.Loading -> {
                    binding.llLoading.visible()
                    binding.tvErrorTip.gone()
                }
                is LoadState.NotLoading -> {
                    binding.tvErrorTip.gone()
                    binding.llLoading.gone()
                }
            }

        }
    }


}