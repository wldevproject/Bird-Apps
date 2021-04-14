package com.cnd.birdapps.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cnd.birdapps.data.api.NetworkClient
import io.reactivex.disposables.CompositeDisposable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    internal fun postData(fullName: String, userName: String, password: String) {
        NetworkClient().apiHttp().apiPostUser(
            fullName, userName, password
        ).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>, response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    _status.value = SUCCESS_MSG
                } else {
                    _status.value = ERROR_MSG
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                _status.value = NO_CONNECTION_MSG
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    companion object{
        private const val NO_CONNECTION_MSG = "Tidak Ada Koneksi"
        private const val ERROR_MSG = "Gagal Mendaftar"
        private const val SUCCESS_MSG = "Berhasil Mendaftar"
    }
}