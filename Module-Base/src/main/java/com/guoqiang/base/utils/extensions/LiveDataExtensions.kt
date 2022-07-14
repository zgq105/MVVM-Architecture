package com.guoqiang.base.utils.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.kotlin.mvvm.repository.api.network.Resource
import com.guoqiang.base.utils.widget.CompleteRecyclerView

/**
 * author: zgq
 * date: 2022/7/13 10:04 下午
 * destcription:
 */


/**
 * Syntactic sugar for [LiveData.observe] function where the [Observer] is the last parameter.
 * Hence can be passed outside the function parenthesis.
 */
inline fun <T> LiveData<T>.observe(owner: LifecycleOwner, crossinline observer: (T) -> Unit) {
    this.observe(owner, Observer { it?.apply(observer) })
}

/**
 * Eliminates the boiler plate on the UI when dealing with `LiveData<Resource<T>>`
 * type from `Repository`.
 * It internally updates the [list] based upon the status and executes
 * the [f] only if status is either SUCCESS or ERROR.
 */
fun <ResultType> Resource<ResultType>.load(list: CompleteRecyclerView, f: (ResultType?) -> Unit) {
    list.showState(status)
    load(f)
}

/**
 * Eliminates the boiler plate on the UI when dealing with `LiveData<Resource<T>>`
 * type from `Repository`.
 * It internally executes the [f] only if status is either SUCCESS or ERROR.
 */
fun <ResultType> Resource<ResultType>.load(f: (ResultType?) -> Unit) {
    if (!status.isLoading()) {
        f(data)
    }
}