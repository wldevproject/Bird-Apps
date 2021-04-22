package com.cnd.birdapps.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cnd.birdapps.data.api.NetworkClient
import com.cnd.birdapps.data.model.url3d.DataItem
import io.reactivex.disposables.CompositeDisposable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 ** Written by CND_Studio 14/03/2021 00.00.
 ** Author @JoeFachrizal
 ** Happy Code...
 **/
class PublishViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    internal fun postData(id: String, publish: Boolean) {
        NetworkClient().apiHttp().apiPutArticle(
            id, publish
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

    internal fun deletData(id: Int) {
        NetworkClient().apiHttp().deleteArticle(id).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>, response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    _status.value = DELETE_MSG
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

    companion object {
        private const val NO_CONNECTION_MSG = "Tidak Ada Koneksi"
        private const val ERROR_MSG = "Artikel Gagal diperbaharui"
        private const val SUCCESS_MSG = "Artikel berhasil diperbaharui"
        private const val DELETE_MSG = "Artikel berhasil dihapus"
    }
}