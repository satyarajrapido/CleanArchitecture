package com.satyaraj.cleanarchitecture.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.satyaraj.cleanarchitecture.domain.Interactor
import com.satyaraj.cleanarchitecture.domain.entities.EmptyText
import com.satyaraj.cleanarchitecture.domain.entities.ErrorText
import com.satyaraj.cleanarchitecture.domain.entities.MainText
import com.satyaraj.cleanarchitecture.domain.entities.ResultMain
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var interactor: Interactor

    private val mutableLiveData = MutableLiveData<MainText>()
    val liveData : LiveData<MainText> = mutableLiveData

    private val mutableErrorLiveData = MutableLiveData<String>()
    val errorLiveData : LiveData<String> = mutableErrorLiveData

    private val mutableEmptyLiveData = MutableLiveData<String>()
    val liveEmptyData : LiveData<String> = mutableEmptyLiveData

    private val compositeDisposable by lazy { CompositeDisposable() }

    fun getText(){
        compositeDisposable.add(
            interactor.fetchMainText()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({handleResponse(it)} , {handleError(it)})
        )
    }

    private fun handleResponse(it: ResultMain) {
        when(it){
            is EmptyText -> mutableEmptyLiveData.postValue(it.value)
            is ErrorText -> mutableErrorLiveData.postValue(it.value)
            is MainText -> mutableLiveData.postValue(it)
        }
    }

    private fun handleError(throwable: Throwable) {
        throwable.printStackTrace()
    }
}