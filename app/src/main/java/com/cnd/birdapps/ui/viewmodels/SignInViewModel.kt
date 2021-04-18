package com.cnd.birdapps.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cnd.birdapps.data.api.NetworkClient
import com.cnd.birdapps.data.model.userLogin.DataLog
import com.cnd.birdapps.data.model.userLogin.UserLogResponse
import com.cnd.birdapps.utils.ConsData.SUCCESS
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _item = MutableLiveData<DataLog>()
    val item: LiveData<DataLog>
        get() = _item

    internal fun postDataLog(userName: String, password: String) {
        NetworkClient().apiHttp().apiLoginUserLog(
            userName, password
        ).enqueue(object : Callback<UserLogResponse> {
            override fun onResponse(
                call: Call<UserLogResponse>,
                response: Response<UserLogResponse>
            ) {
                if (response.body()?.status == SUCCESS) {
                    if (response.body()?.data != null) {
                        _item.value = response.body()?.data
                        _status.value = SUCCESS_MSG + response.body()!!.data.name
                    } else {
                        _status.value = ERROR_MSG
                    }
                } else {
                    _status.value = ERROR_MSG
                }
            }

            override fun onFailure(call: Call<UserLogResponse>, t: Throwable) {
                _status.value = NO_CONNECTION_MSG
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    companion object {
        private const val NO_CONNECTION_MSG = "Tidak Ada Koneksi"
        private const val ERROR_MSG = "Gagal Masuk"
        private const val SUCCESS_MSG = "Selamat Datang "
    }
}