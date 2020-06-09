package com.teo.hospitalapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.teo.hospitalapp.data.HospitalRepository
import com.teo.hospitalapp.ui.data.HospitalDetailViewModel
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class HospitalDetailViewModelTest {

    private val orgId = 12323

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()


    private val repository = mock(HospitalRepository::class.java)
    private var viewModel = HospitalDetailViewModel(repository)

    @Test
    fun testNull() {
        assertThat(viewModel.hospital, nullValue())
        verify(repository, never()).observeHospital(orgId)
    }

    @Test
    fun doNotFetchWithoutObservers() {
        viewModel.orgId = orgId
        verify(repository, never()).observeHospital(orgId)
    }

    @Test
    fun doNotFetchWithoutObserverOnConnectionChange() {
        viewModel.orgId = orgId

        verify(repository, never()).observeHospital(orgId)

    }
}