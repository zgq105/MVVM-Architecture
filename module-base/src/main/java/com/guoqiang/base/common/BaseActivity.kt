package com.guoqiang.base.common

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.guoqiang.base.widget.LoadingDialog

/**
 * author: zgq
 * date: 2022/7/13 10:01 下午
 * destcription:
 */
abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: V
    private lateinit var loadingDialog: LoadingDialog

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = createBinding()
        this.binding = binding
        setContentView(binding.root)
        loadingDialog = LoadingDialog(this, false)

        initData()
    }

    /**
     * 子类实现业务逻辑
     */
    abstract fun initData()

    abstract fun createBinding(): V

    /**
     * show 加载中
     */
    protected fun showLoading() {
        loadingDialog.showDialog(this, false)
    }

    /**
     * dismiss loading dialog
     */
    protected fun dismissLoading() {
        loadingDialog.dismissDialog()
    }
}