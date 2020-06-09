package com.teo.hospitalapp.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url


interface ApiService {

    companion object {
        const val ENDPOINT = "https://media.nhschoices.nhs.uk/data/foi/"
        const val fileName = "Hospital.csv"
    }
    @Streaming
    @GET
   suspend fun downloadFile(@Url file: String): Response<ResponseBody>

}

//class ApiServiceImpl : ApiService {
//
//    private val service = ApiClient.getRetrofit().create(ApiService::class.java)
//
//    override fun downloadFile(): Call<ResponseBody> =
//        service.downloadFile()
//}