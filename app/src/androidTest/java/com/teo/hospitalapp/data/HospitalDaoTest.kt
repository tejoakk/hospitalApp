package com.teo.hospitalapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.teo.hospitalapp.util.getValue
import com.teo.hospitalapp.util.testhos1
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HospitalDaoTest : DbTest() {
    private lateinit var hospitalDao: HospitalDao
    private val hos = testhos1.copy()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before fun createDb() {
        hospitalDao = db.hospitalDao()

        runBlocking {
            hospitalDao.insertAll(listOf(hos))
        }
    }

    @Test fun testGetMeals() {
        val list = getValue(hospitalDao.getHospitals())
        assertThat(list.size, equalTo(1))

        assertThat(list[0], equalTo(hos))
    }

    @Test fun testGetHospital() {
        assertThat(getValue(hos.organisationID?.let { hospitalDao.getHospital(it) }), equalTo(hos))
    }
}