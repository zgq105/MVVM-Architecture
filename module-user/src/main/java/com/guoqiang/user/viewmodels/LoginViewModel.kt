package com.guoqiang.user.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.guoqiang.base.common.BaseViewModel
import com.guoqiang.base.network.BaseResponse
import com.guoqiang.user.data.LoginRepository
import com.guoqiang.user.data.dto.LoginEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: LoginRepository) : BaseViewModel() {

    val loginLiveData: LiveData<BaseResponse<LoginEntity>>
        get() = _loginLiveData
    val registerLiveData: LiveData<BaseResponse<LoginEntity>>
        get() = _registerLiveData

    private var _loginLiveData = MutableLiveData<BaseResponse<LoginEntity>>()
    private val _registerLiveData = MutableLiveData<BaseResponse<LoginEntity>>()


    fun login(userName: String, password: String) {
        viewModelScope.launch {
            val response = repo.login(userName, password)
            _loginLiveData.value = response
        }
    }

    fun register(
        userName: String,
        password: String,
        rePassword: String,
    ) {
        viewModelScope.launch {
            val response = repo.register(userName, password, rePassword)
            _registerLiveData.value = response
        }
    }


    fun test() {

    }



}
