package com.cnd.birdapps.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cnd.birdapps.data.api.NetworkClient
import com.cnd.birdapps.data.model.kategory.DataItemKat
import com.cnd.birdapps.data.model.kategory.KategoryResponse
import com.cnd.birdapps.utils.ConsData.SUCCESS
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

/**
 ** Written by CND_Studio 14/03/2021 00.00.
 ** Author @JoeFachrizal
 ** Happy Code...
 **/
class KategoryViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _item = MutableLiveData<ArrayList<DataItemKat>>()
    val items: LiveData<ArrayList<DataItemKat>>
        get() = _item

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    internal fun getData(charSequence: CharSequence) {
        NetworkClient().apiHttp().apiGetBirdSpecies().enqueue(object : Callback<KategoryResponse> {
            override fun onResponse(
                call: Call<KategoryResponse>, response: Response<KategoryResponse>
            ) {
                if (response.body()?.status == SUCCESS) {
                    if (response.body()?.data != null) {
                        val dataAsliKat: ArrayList<DataItemKat> = response.body()?.data!!
                        var dataFilerKat: ArrayList<DataItemKat> = arrayListOf()
                        val charSearch = charSequence.toString()
                        dataFilerKat = if (charSequence.isEmpty()) dataAsliKat
                        else {
                            for (data in dataAsliKat) {
                                if (data.name.toLowerCase(Locale.ROOT)
                                        .contains(charSearch.toLowerCase(Locale.ROOT))
                                ) {
                                    dataFilerKat.add(data)
                                }
                            }
                            dataFilerKat
                        }

                        _item.value = dataFilerKat
                    } else {
                        _status.value = "Data Tidak Ditemukan"
                    }
                } else {
                    _status.value = "Akses Bermasalah"
                }

            }

            override fun onFailure(call: Call<KategoryResponse>, t: Throwable) {
                _status.value = "Tidak Ada Koneksi"
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}