package com.guoqiang.homepage.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.guoqiang.base.common.BaseFragment
import com.guoqiang.base.network.BaseResponse
import com.guoqiang.base.network.PageWrapperDto
import com.guoqiang.homepage.R
import com.guoqiang.homepage.data.dto.CarCircle
import com.guoqiang.homepage.databinding.FragmentHomePageBinding
import com.guoqiang.homepage.ui.home.FocusOnFragment
import com.guoqiang.homepage.ui.home.LiveFragment
import com.guoqiang.homepage.ui.home.RecommendFragment
import com.guoqiang.homepage.ui.home.ShortVideoFragment

/**
 * author: zgq
 * date: 2022/8/23 8:07 下午
 * destcription:
 */
class HomePageFragment : BaseFragment<FragmentHomePageBinding>() {
    private lateinit var homeTabAdapter: HomeTabAdapter
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomePageBinding {
        return FragmentHomePageBinding.inflate(layoutInflater)
    }

    override fun initData() {

    }

    override fun initView() {
        homeTabAdapter = HomeTabAdapter(this)
        binding.vpPager.adapter = homeTabAdapter
        TabLayoutMediator(binding.tabLayout, binding.vpPager) { tab, position ->
            tab.text = homeTabAdapter.getTitle(position)
        }.attach()
    }

    override fun onResume() {
        super.onResume()

    }

    class HomeTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        private val fragments = arrayListOf<Fragment>(
            FocusOnFragment(),
            RecommendFragment(),
            ShortVideoFragment(),
            LiveFragment()
        )

        private val tabTitles = arrayListOf(
            fragment.getString(R.string.home_focus_on),
            fragment.getString(R.string.home_recommend),
            fragment.getString(R.string.home_short_video),
            fragment.getString(R.string.home_live)
        )

        fun getTitle(position: Int): String {
            return tabTitles[position]
        }

        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }




}