package com.satyaraj.cleanarchitecture.data

import android.content.res.Resources
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.satyaraj.cleanarchitecture.R
import com.satyaraj.cleanarchitecture.base.BaseTest
import com.satyaraj.cleanarchitecture.data.remote.MainApi
import com.satyaraj.cleanarchitecture.domain.entities.MainResponse

import io.reactivex.Single
import org.junit.Assert
import org.junit.Test


class MainRepositoryImplTest : BaseTest() {

    private val resources = mock<Resources>()
    private val api = mock<MainApi>()
    private val repositoryImpl = MainRepositoryImpl(api, resources)

    @Test
    fun `should fetch text`() {
        val mockResponse = MainResponse(10)
        whenever(api.fetchMainText()).thenReturn(Single.just(mockResponse))
        repositoryImpl.fetchText().test().assertValue {
            result -> result == mockResponse
        }
    }

    @Test
    fun `should return error message`() {
        whenever(resources.getString(R.string.something_went_wrong)).thenReturn("dummy")
        Assert.assertEquals("dummy", repositoryImpl.getErrorMessage())
    }

    @Test
    fun `should return empty message`() {
        whenever(resources.getString(R.string.no_values)).thenReturn("dummy")
        Assert.assertEquals("dummy", repositoryImpl.getEmptyMessage())
    }

    @Test
    fun `should return non empty message`() {
        whenever(resources.getString(R.string.non_empty_text)).thenReturn("dummy")
        Assert.assertEquals("dummy", repositoryImpl.getListLoadedMessage())

    }
}