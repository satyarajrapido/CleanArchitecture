package com.satyaraj.cleanarchitecture.data

import android.content.res.Resources
import com.satyaraj.cleanarchitecture.R
import com.satyaraj.cleanarchitecture.data.remote.MainApi
import com.satyaraj.cleanarchitecture.domain.abstraction.MainRepository

class MainRepositoryImpl(
    private val mainApi: MainApi,
    private val resources: Resources
) : MainRepository {

    override fun fetchText() =
        mainApi.fetchMainText()

    override fun getErrorMessage() =
        resources.getString(R.string.something_went_wrong)

    override fun getEmptyMessage() =
        resources.getString(R.string.no_values)

    override fun getListLoadedMessage() =
        resources.getString(R.string.non_empty_text)
}