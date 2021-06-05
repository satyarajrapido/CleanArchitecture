package com.satyaraj.cleanarchitecture.domain

import com.satyaraj.cleanarchitecture.domain.abstraction.MainRepository
import com.satyaraj.cleanarchitecture.domain.entities.EmptyText
import com.satyaraj.cleanarchitecture.domain.entities.ErrorText
import com.satyaraj.cleanarchitecture.domain.entities.MainText

class FetchMainText(private val mainRepository: MainRepository) {
    operator fun invoke() = mainRepository.fetchText().map {
        when (it.number) {
            null -> {
                return@map ErrorText(mainRepository.getErrorMessage())
            }
            0 -> {
                return@map EmptyText(mainRepository.getEmptyMessage())
            }
            else -> {
                return@map MainText(mainRepository.getListLoadedMessage(), it.number)
            }
        }
    }
}