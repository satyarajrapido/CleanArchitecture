package com.satyaraj.cleanarchitecture.data.remote

import com.satyaraj.cleanarchitecture.domain.entities.MainResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MainApi {
    @GET("http://private-c7fdfc-stamps.apiary-mock.com/number")
    fun fetchMainText() : Single<MainResponse>
}