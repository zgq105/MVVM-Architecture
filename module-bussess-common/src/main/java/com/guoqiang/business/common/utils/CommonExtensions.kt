package com.guoqiang.business.common.utils

import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import com.guoqiang.base.common.BaseFragment
import com.guoqiang.base.utils.extensions.gone
import com.guoqiang.business.common.R
import com.guoqiang.business.common.databinding.LayoutNetworkErrorBinding

/**
 * author: zgq
 * date: 2022/7/13 10:04 下午
 * destcription:
 */

fun BaseFragment<*>.showNetworkError() {
    val viewRoot = binding.root
    val addedErrorView = viewRoot.findViewById<View>(R.id.cl_common_network_error)
    if (addedErrorView != null && addedErrorView.isVisible) {
        return
    }
    if (viewRoot is ConstraintLayout) {
        val errorView = LayoutNetworkErrorBinding.inflate(layoutInflater).root
        val lp = viewRoot.children.first()?.layoutParams as? ConstraintLayout.LayoutParams
        lp?.let {
            lp.width = ConstraintLayout.LayoutParams.MATCH_PARENT
            lp.height = ConstraintLayout.LayoutParams.MATCH_PARENT
            errorView.layoutParams = lp
        }
        viewRoot.addView(errorView)
    }
}

fun BaseFragment<*>.hideNetworkError() {
    val viewRoot = binding.root
    if (viewRoot is ConstraintLayout) {
        val errorView = viewRoot.findViewById<View>(R.id.cl_common_network_error)
        errorView?.let {
            viewRoot.removeView(it)
        }
    }
}