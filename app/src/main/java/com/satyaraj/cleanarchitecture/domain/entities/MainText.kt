package com.satyaraj.cleanarchitecture.domain.entities

sealed class ResultMain
data class MainText(val value: String, val number : Int) : ResultMain()
data class EmptyText(val value: String) : ResultMain()
data class ErrorText(val value: String) : ResultMain()

