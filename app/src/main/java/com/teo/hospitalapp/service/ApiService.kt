package com.teo.hospitalapp.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming



interface ApiService {

    companion object {
        const val ENDPOINT = "http://media.nhschoices.nhs.uk/data/foi/Hospital.csv"
    }
    @Streaming
    @GET
   suspend fun downloadFile(): Response<Any>

}

//class ApiServiceImpl : ApiService {
//
//    private val service = ApiClient.getRetrofit().create(ApiService::class.java)
//
//    override fun downloadFile(): Call<ResponseBody> =
//        service.downloadFile()
//}