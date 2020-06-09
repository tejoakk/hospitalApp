package com.teo.hospitalapp.data

import com.teo.hospitalapp.service.ApiService
import com.teo.hospitalapp.service.ApiService.Companion.ENDPOINT
import com.teo.hospitalapp.service.ApiService.Companion.fileName
import javax.inject.Inject

/**
 * Works with the API to get data.
 */
class HospitalRemoteDataSource @Inject constructor(private val service: ApiService) : BaseDataSource() {

    suspend fun fetchData()
            = getResult { service.downloadFile(fileName) }

}
