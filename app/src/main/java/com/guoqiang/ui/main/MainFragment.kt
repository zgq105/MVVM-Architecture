package com.guoqiang.ui.main

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.guoqiang.Base64Utils
import com.guoqiang.base.common.BaseFragment
import com.guoqiang.databinding.FragmentMainBinding
import java.text.SimpleDateFormat

class MainFragment : BaseFragment<FragmentMainBinding>() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private fun initListener() {
        binding.message.setOnClickListener {
            //ARouter.getInstance().build(MainActivity3.PATH).navigation()
            //ARouter.getInstance().build(Constants.PATH_LOGIN).navigation()
            test2()
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

    }

    override fun initView() {
        initListener()
    }

    private fun test() {
        val pushInfoBean = PushInfoBean("http://www.baidu.com", "123", "1072699480150020096", 4)
        val ext = Base64Utils.encode(Gson().toJson(pushInfoBean).toByteArray())
        val intent = Intent()
        val componentName = ComponentName(
            "com.afanticar.aivideo",
            "com.afanticar.common.Jpush.JpushRouterActivity"
        )
        intent.putExtra("ext", ext)
        intent.component = componentName
        startActivity(intent)
    }

    private fun test2() {

    }

    class PushInfoBean(val sourceUrl: String?, val msgId: String?, val logId: String?, @SerializedName("type") val type: Int = 0)

}