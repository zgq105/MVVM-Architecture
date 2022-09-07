package com.guoqiang.homepage.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.QuickViewHolder
import com.guoqiang.base.common.BaseFragment
import com.guoqiang.base.common.paging.BasePagingAdapter
import com.guoqiang.base.utils.LogUtil
import com.guoqiang.base.utils.load
import com.guoqiang.homepage.R
import com.guoqiang.homepage.data.dto.CarCircle
import com.guoqiang.homepage.databinding.FragmentCarCircleBinding
import com.guoqiang.homepage.databinding.LayoutCarCricleListItemBinding
import com.guoqiang.homepage.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * author: zgq
 * date: 2022/8/23 8:07 下午
 * destcription:
 */
@AndroidEntryPoint
class CarCircleFragment : BaseFragment<FragmentCarCircleBinding>() {

    private val homeViewModel: HomeViewModel by viewModels()

    private val adapter = CarCircleAdapter()

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCarCircleBinding {
        return FragmentCarCircleBinding.inflate(layoutInflater)
    }

    override fun initData() {
        val refreshLayout = binding.refreshLayout
        refreshLayout.setOnRefreshListener {
            LogUtil.debug(TAG, "setOnRefreshListener")
            adapter.refresh()
        }
        refreshLayout.setOnLoadMoreListener {
            LogUtil.debug(TAG, "setOnLoadMoreListener")
        }

//        homeViewModel.carCircleListLiveData.observe(viewLifecycleOwner) {
//            refreshLayout.finishRefresh()
//        }
        //refreshLayout.autoRefresh()

        binding.recyclerview.layoutManager = LinearLayoutManager(this.requireContext())
        binding.recyclerview.adapter = adapter

        loadData()
    }

    private fun loadData() {
        lifecycleScope.launch {
            homeViewModel.getCarCircleList().collectLatest {
                LogUtil.debug(TAG, it.toString())
                adapter.submitData(it)
                binding.refreshLayout.finishRefresh()
            }
        }
    }

    class CarCircleAdapter : BasePagingAdapter<CarCircle>(diffCallback) {

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