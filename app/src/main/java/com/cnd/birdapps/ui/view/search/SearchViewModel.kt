package com.cnd.birdapps.ui.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cnd.birdapps.data.api.NetworkClient
import com.cnd.birdapps.data.model.article.ArticleResponse
import com.cnd.birdapps.data.model.article.DataItem
import com.cnd.birdapps.utils.ConsData
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {
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
                if (response.body()?.status == ConsData.SUCCESS) {
                    if (response.body()?.data != null) {
                        _item.value = response.body()?.data
                    } else {
                        _status.value = ERROR_MSG
                    }
                } else {
                    _status.value = NO_CONNECTION_MSG
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

    companion object{
        private const val NO_CONNECTION_MSG = "Tidak Ada Koneksi"
        private const val ERROR_MSG = "Data Tidak Ditemukan"
    }
}