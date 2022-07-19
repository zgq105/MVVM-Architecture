package com.guoqiang

import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter
import com.guoqiang.base.common.BaseActivity
import com.guoqiang.business.common.ARouterConfig
import com.guoqiang.databinding.ActivityMainBinding
import com.guoqiang.ui.main.MainFragment

class MainActivity: BaseActivity<ActivityMainBinding>() {
    override fun initData() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .commitNow()
    }

    override fun createBinding(): ActivityMainBinding {
       return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onResume() {
        super.onResume()
    }
}