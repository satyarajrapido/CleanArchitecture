package com.satyaraj.cleanarchitecture.domain.abstraction

import com.satyaraj.cleanarchitecture.domain.entities.MainResponse
import io.reactivex.Single

interface MainRepository {
    fun fetchText() : Single<MainResponse>
    fun getErrorMessage() : String
    fun getEmptyMessage() : String
    fun getListLoadedMessage() : String
}