package com.guoqiang.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter
import com.guoqiang.MainActivity3
import com.guoqiang.R
import com.guoqiang.base.common.BaseFragment
import com.guoqiang.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private fun initListener() {
        binding?.message?.setOnClickListener {
            ARouter.getInstance().build(MainActivity3.PATH).navigation()
        }
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun initData() {
        initListener()
    }

}