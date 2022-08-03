package com.guoqiang.user.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.guoqiang.base.common.BaseActivity
import com.guoqiang.base.network.DataState
import com.guoqiang.base.utils.extensions.toast
import com.guoqiang.business.common.Constants.Companion.PATH_LOGIN
import com.guoqiang.user.R
import com.guoqiang.user.databinding.ActivityLoginBinding
import com.guoqiang.user.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = PATH_LOGIN)
class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    private val loginViewModel: LoginViewModel by viewModels()

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initListener() {
        super.initListener()
        binding.login.setOnClickListener {
            login()
        }
    }

    override fun initData() {
        loginViewModel.loginLiveData.observe(this) {
            if (it.isSuccess) {
                toast("登录成功")
            } else {
                toast(it.getErrorMsg(this))
            }
        }
    }

    override fun createBinding(): ActivityLoginBinding {
       return ActivityLoginBinding.inflate(layoutInflater)
    }

    private fun login(){
        val username = binding.username.text.trim().toString()
        if (username.isNullOrEmpty()) {
            toast(getString(R.string.user_username_empty))
            return
        }
        val password = binding.password.text.trim().toString()
        if (password.isNullOrEmpty()) {
            toast(getString(R.string.user_password_empty))
            return
        }
        loginViewModel.login(username,password)
    }
}