package com.cnd.birdapps.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cnd.birdapps.R
import com.cnd.birdapps.data.model.Mastering
import io.reactivex.disposables.CompositeDisposable

class PlayMasteringViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _loveBird = MutableLiveData<ArrayList<Mastering>>().apply {
        val arrayList: java.util.ArrayList<Mastering> = arrayListOf()
        arrayList.add(Mastering("Master lovebird 1", "Lovebird", R.raw.master_lovebird_1))
        arrayList.add(Mastering("Master lovebird 2", "Lovebird", R.raw.master_lovebird_2))
        arrayList.add(Mastering("Master lovebird 3", "Lovebird", R.raw.master_lovebird_3))
        arrayList.add(Mastering("Master lovebird 4", "Lovebird", R.raw.master_lovebird_4))
        arrayList.add(Mastering("Master lovebird 5", "Lovebird", R.raw.master_lovebird_5))
        arrayList.add(Mastering("Master lovebird 6", "Lovebird", R.raw.master_lovebird_6))
        arrayList.add(Mastering("Master lovebird 7", "Lovebird", R.raw.master_lovebird_7))
        arrayList.add(Mastering("Master lovebird 8", "Lovebird", R.raw.master_lovebird_8))
        value = arrayList
    }

    private val _kenari = MutableLiveData<ArrayList<Mastering>>().apply {
        val arrayList: java.util.ArrayList<Mastering> = arrayListOf()
        arrayList.add(Mastering("Master kenari 1", "Kenari", R.raw.master_kenari_1))
        arrayList.add(Mastering("Master kenari 2", "Kenari", R.raw.master_kenari_2))
        arrayList.add(Mastering("Master kenari 3", "Kenari", R.raw.master_kenari_3))
        arrayList.add(Mastering("Master kenari 4", "Kenari", R.raw.master_kenari_4))
        arrayList.add(Mastering("Master kenari 5", "Kenari", R.raw.master_kenari_5))
        arrayList.add(Mastering("Master kenari 6", "Kenari", R.raw.master_kenari_6))
        value = arrayList
    }

    private val _jalakrio = MutableLiveData<ArrayList<Mastering>>().apply {
        val arrayList: java.util.ArrayList<Mastering> = arrayListOf()
        arrayList.add(Mastering("Master jalak rio 1", "Jalak rio", R.raw.master_jalak_rio_1))
        arrayList.add(Mastering("Master jalak rio 2", "Jalak rio", R.raw.master_jalak_rio_2))
        arrayList.add(Mastering("Master jalak rio 3", "Jalak rio", R.raw.master_jalak_rio_3))
        arrayList.add(Mastering("Master jalak rio 4", "Jalak rio", R.raw.master_jalak_rio_4))
        arrayList.add(Mastering("Master jalak rio 5", "Jalak rio", R.raw.master_jalak_rio_5))
        arrayList.add(Mastering("Master jalak rio 6", "Jalak rio", R.raw.master_jalak_rio_6))
        value = arrayList
    }

    private val _cililin = MutableLiveData<ArrayList<Mastering>>().apply {
        val arrayList: java.util.ArrayList<Mastering> = arrayListOf()
        arrayList.add(Mastering("Master cililin 1", "Cililin", R.raw.master_cililin_1))
        arrayList.add(Mastering("Master cililin 2", "Cililin", R.raw.master_cililin_2))
        arrayList.add(Mastering("Master cililin 3", "Cililin", R.raw.master_cililin_3))
        arrayList.add(Mastering("Master cililin 4", "Cililin", R.raw.master_cililin_4))
        arrayList.add(Mastering("Master cililin 5", "Cililin", R.raw.master_cililin_5))
        arrayList.add(Mastering("Master cililin 6", "Cililin", R.raw.master_cililin_6))
        value = arrayList
    }

    private val _cucakjenggot = MutableLiveData<ArrayList<Mastering>>().apply {
        val arrayList: java.util.ArrayList<Mastering> = arrayListOf()
        arrayList.add(Mastering("Master cucak jenggot 1", "Cucak jenggot", R.raw.master_cucak_jenggot_1))
        arrayList.add(Mastering("Master cucak jenggot 2", "Cucak jenggot", R.raw.master_cucak_jenggot_2))
        arrayList.add(Mastering("Master cucak jenggot 3", "Cucak jenggot", R.raw.master_cucak_jenggot_3))
        arrayList.add(Mastering("Master cucak jenggot 4", "Cucak jenggot", R.raw.master_cucak_jenggot_4))
        arrayList.add(Mastering("Master cucak jenggot 5", "Cucak jenggot", R.raw.master_cucak_jenggot_5))
        arrayList.add(Mastering("Master cucak jenggot 6", "Cucak jenggot", R.raw.master_cucak_jenggot_6))
        arrayList.add(Mastering("Master cucak jenggot 7", "Cucak jenggot", R.raw.master_cucak_jenggot_7))
        value = arrayList
    }

    val loveBird: LiveData<ArrayList<Mastering>> = _loveBird
    val kenari: LiveData<ArrayList<Mastering>> = _kenari
    val jalakrio: LiveData<ArrayList<Mastering>> = _jalakrio
    val cililin: LiveData<ArrayList<Mastering>> = _cililin
    val cucakjenggot: LiveData<ArrayList<Mastering>> = _cucakjenggot

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}