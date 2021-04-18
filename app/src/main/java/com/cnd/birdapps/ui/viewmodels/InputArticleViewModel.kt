package com.cnd.birdapps.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cnd.birdapps.data.api.NetworkClient
import io.reactivex.disposables.CompositeDisposable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 ** Written by CND_Studio 14/03/2021 00.00.
 ** Author @JoeFachrizal
 ** Happy Code...
 **/
class InputArticleViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    internal fun setArticlePost(
        userId: RequestBody,
        birdSpecies: RequestBody,
        description: RequestBody,
        image: MultipartBody.Part
    ) {
        NetworkClient().apiHttp().apiPostArticle(
            userId, birdSpecies, description, image
        ).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>, response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    _status.value = "Berhasil Dikirim"
                } else {
                    _status.value = "Gagal Dikirim"
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                _status.value = "Tidak Ada Koneksi"
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}