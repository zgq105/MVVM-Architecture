package com.guoqiang.homepage.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.guoqiang.base.common.BaseFragment
import com.guoqiang.homepage.databinding.FragmentFocusOnBinding
import com.guoqiang.homepage.databinding.FragmentHomePageBinding
import com.guoqiang.homepage.databinding.FragmentSelectCarBinding
import com.guoqiang.homepage.databinding.FragmentUsedCarBinding

/**
 * author: zgq
 * date: 2022/8/23 8:07 下午
 * destcription:
 */
class FocusOnFragment : BaseFragment<FragmentFocusOnBinding>() {
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentFocusOnBinding {
        return FragmentFocusOnBinding.inflate(layoutInflater)
    }

    override fun initData() {

    }

    override fun initView() {

    }

}