package com.satyaraj.cleanarchitecture.base

import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
abstract class BaseTest {
    @JvmField
    @Rule
    val rxRule = Rx2SchedulersOverrideRule()
}