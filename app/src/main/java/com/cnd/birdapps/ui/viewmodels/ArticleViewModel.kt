package com.cnd.birdapps.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cnd.birdapps.data.api.NetworkClient
import com.cnd.birdapps.data.model.article.ArticleResponse
import com.cnd.birdapps.data.model.article.DataItem
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
class ArticleViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _item = MutableLiveData<ArrayList<DataItem>>()
    val items: LiveData<ArrayList<DataItem>>
        get() = _item

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    internal fun getData() {
        NetworkClient().apiHttp().apiGetArticle().enqueue(object : Callback<ArticleResponse> {
            override fun onResponse(
                call: Call<ArticleResponse>, response: Response<ArticleResponse>
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

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                _status.value = "Tidak Ada Koneksi"
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}