package com.cnd.birdapps.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cnd.birdapps.data.api.NetworkClient
import com.cnd.birdapps.data.model.user.DataItem
import com.cnd.birdapps.data.model.user.UserResponse
import com.cnd.birdapps.utils.ConsData.SUCCESS
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
class UserViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _item = MutableLiveData<ArrayList<DataItem>>()
    val items: LiveData<ArrayList<DataItem>>
        get() = _item

    private val _status = MutableLiveData<String>()
    val failure: LiveData<String>
        get() = _status

    internal fun setData(type: String) {
        NetworkClient().apiHttp().apiGetUser(type).enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>, response: Response<UserResponse>
            ) {
                if (response.body()?.status == SUCCESS) {
                    if (response.body()?.data != null) {
                        _item.value = response.body()?.data
                    } else {
                        _status.value = "Data Tidak Ditemukan"
                    }
                } else {
                    _status.value = "Akses Bermasalah"
                }

            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _status.value = "Tidak Ada Koneksi"
            }
        })
    }

    internal fun setDataPost() {
        NetworkClient().apiHttp().apiPostUser(
            "dudu","dudu","321"
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

    internal fun setArticlePost(
        userId: RequestBody,
        birdSpecies: RequestBody,
        description: RequestBody,
        image: MultipartBody.Part
    ) {
        NetworkClient().apiHttp().apiPostArticle(
            userId,birdSpecies,description,image
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