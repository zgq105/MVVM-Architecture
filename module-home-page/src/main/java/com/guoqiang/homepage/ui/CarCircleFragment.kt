package com.guoqiang.homepage.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guoqiang.base.common.BaseFragment
import com.guoqiang.base.common.paging.BasePagingAdapter
import com.guoqiang.base.common.paging.BasePagingFooterAdapter
import com.guoqiang.base.utils.LogUtil
import com.guoqiang.base.utils.extensions.visible
import com.guoqiang.base.utils.load
import com.guoqiang.business.common.utils.resetNormal
import com.guoqiang.business.common.utils.showEmptyView
import com.guoqiang.business.common.utils.showNetworkError
import com.guoqiang.homepage.R
import com.guoqiang.homepage.data.dto.CarCircle
import com.guoqiang.homepage.databinding.FragmentCarCircleBinding
import com.guoqiang.homepage.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

/**
 * author: zgq
 * date: 2022/8/23 8:07 下午
 * destcription:
 */
@AndroidEntryPoint
class CarCircleFragment : BaseFragment<FragmentCarCircleBinding>() {

    private val homeViewModel: HomeViewModel by viewModels()
    private val adapter = CarCircleAdapter()
    private val pagingFooterAdapter = BasePagingFooterAdapter()

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCarCircleBinding {
        return FragmentCarCircleBinding.inflate(layoutInflater)
    }

    override fun initView() {
        val refreshLayout = binding.refreshLayout
        binding.recyclerview.layoutManager = LinearLayoutManager(this.requireContext())
        binding.recyclerview.adapter =
            adapter.withLoadStateFooter(pagingFooterAdapter)

        refreshLayout.setOnRefreshListener {
            adapter.refresh()
        }

        adapter.setOnItemClick {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:13435666677"))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        pagingFooterAdapter.setOnItemClick {
            adapter.retry()
        }
    }

    override fun initData() {
        LogUtil.debug(TAG, "initData")
        loadData()
        loadStatesListener()
    }

    private fun loadData() {
        lifecycleScope.launchWhenCreated {
            homeViewModel.getCarCircleList().collectLatest {
                LogUtil.debug(TAG, it.toString())
                adapter.submitData(it)
                binding.refreshLayout.finishRefresh()
                binding.recyclerview.scrollToPosition(0)
            }
        }
    }

    private fun loadStatesListener() {
        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collectLatest { loadState ->
                LogUtil.debug(TAG, "loadStateFlow:$loadState")
                when (loadState.refresh) {
                    is LoadState.Loading -> showLoading()
                    is LoadState.NotLoading -> {
                        dismissLoading()
                        resetNormal()
                        if (adapter.itemCount == 0) {
                            showEmptyView()
                        }
                    }
                    is LoadState.Error -> {
                        dismissLoading()
                        showNetworkError()
                    }
                }
            }
        }
    }


    class CarCircleAdapter : BasePagingAdapter<CarCircle>(diffCallback) {

        private var onItemClick: ((CarCircle?) -> Unit)? = null

        fun setOnItemClick(onItemClick: (CarCircle?) -> Unit) {
            this.onItemClick = onItemClick
        }

        companion object {
            private val diffCallback = object : DiffUtil.ItemCallback<CarCircle>() {
                override fun areItemsTheSame(oldItem: CarCircle, newItem: CarCircle): Boolean {
                    return oldItem.carCircleId == newItem.carCircleId
                }

                override fun areContentsTheSame(oldItem: CarCircle, newItem: CarCircle): Boolean {
                    return oldItem == newItem
                }
            }
        }

        override fun getItemLayout(position: Int): Int {
            return R.layout.layout_car_cricle_list_item
        }

        override fun onItemClick(data: CarCircle?) {
            onItemClick?.invoke(data)
        }

        override fun bindData(helper: ItemHelper, data: CarCircle?) {
            helper.setText(R.id.tv_name, data?.name)
            helper.setText(R.id.tv_title, data?.title)
            helper.setText(R.id.tv_content, data?.content)
            val context = helper.itemView.context
            helper.setText(
                R.id.tv_active_rider,
                context.getString(
                    R.string.home_active_rider,
                    data?.activeRider.toString()
                )
            )
            helper.setText(
                R.id.tv_certified_car_owner,
                context.getString(
                    R.string.home_certified_car_owner,
                    data?.certifiedCarOwner.toString()
                )
            )
            val ivAvatar = helper.findViewById(R.id.iv_avatar) as AppCompatImageView
            ivAvatar.load(context, data?.avatar ?: "")
        }
    }

    override fun onResume() {
        super.onResume()
    }

    companion object {
        private const val TAG = "CarCircleFragment"
    }

}