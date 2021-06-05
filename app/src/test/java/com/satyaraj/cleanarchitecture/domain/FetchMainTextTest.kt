package com.satyaraj.cleanarchitecture.domain

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.satyaraj.cleanarchitecture.base.BaseTest
import com.satyaraj.cleanarchitecture.domain.abstraction.MainRepository
import com.satyaraj.cleanarchitecture.domain.entities.EmptyText
import com.satyaraj.cleanarchitecture.domain.entities.ErrorText
import com.satyaraj.cleanarchitecture.domain.entities.MainResponse
import com.satyaraj.cleanarchitecture.domain.entities.MainText
import io.reactivex.Single
import org.junit.Test

class FetchMainTextTest : BaseTest() {

    private val repository = mock<MainRepository>()
    private val usecase = FetchMainText(repository)

    @Test
    fun `should return the number has value`() {
        whenever(repository.getListLoadedMessage()).thenReturn(FULL)
        val mockValue = MainResponse(10)
        whenever(repository.fetchText()).thenReturn(Single.just(mockValue))

        val nonEmptyText = MainText(FULL, 10)
        usecase.invoke().test().assertValue { response -> response == nonEmptyText }
    }

    @Test
    fun `should return no value`() {
        whenever(repository.getEmptyMessage()).thenReturn(EMPTY)
        val mockValue = MainResponse(0)
        whenever(repository.fetchText()).thenReturn(Single.just(mockValue))

        val emptyText = EmptyText(EMPTY)
        usecase.invoke().test().assertValue { response -> response == emptyText }
    }

    @Test
    fun `should return something went wrong`() {
        whenever(repository.getErrorMessage()).thenReturn(ERROR)
        val mockValue = MainResponse(null)
        whenever(repository.fetchText()).thenReturn(Single.just(mockValue))

        val errorText = ErrorText(ERROR)
        usecase.invoke().test().assertValue { response -> response == errorText }
    }

    companion object {
        const val FULL = "full"
        const val EMPTY = "empty"
        const val ERROR = "error"
    }
}
