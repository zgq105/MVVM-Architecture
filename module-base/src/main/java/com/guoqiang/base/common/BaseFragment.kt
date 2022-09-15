package com.guoqiang.base.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.guoqiang.base.utils.LogUtil
import com.guoqiang.base.widget.LoadingDialog

/**
 * author: zgq
 * date: 2022/7/13 10:01 下午
 * destcription:
 */
abstract class BaseFragment<V : ViewBinding> : Fragment() {
    protected lateinit var binding: V
    private lateinit var loadingDialog: LoadingDialog
    private var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (this.rootView == null) {
            val binding = onCreateBinding(inflater, container, savedInstanceState)
            this.binding = binding
            this.rootView = this.binding.root
            initView()
        }
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog = LoadingDialog(this.requireContext(), false)
        initData()
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
    }

    abstract fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): V

    /**
     * show 加载中
     */
    protected fun showLoading() {
        loadingDialog.showDialog(this.requireContext(), false)
    }

    /**
     * 子类实现业务逻辑
     */
    abstract fun initData()

    abstract fun initView()

    /**
     * dismiss loading dialog
     */
    protected fun dismissLoading() {
        loadingDialog.dismissDialog()
    }
}