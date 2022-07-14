package com.guoqiang.base.ui

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * author: zgq
 * date: 2022/7/13 10:01 下午
 * destcription:
 */
abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: V

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = createBinding()
        this.binding = binding
        setContentView(binding.root)
    }

    abstract fun createBinding(): V
}