package com.example.ducktales

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Url

class ApiClient {
}

interface ApiService
{
    @POST
    fun sendEmail(
        @Url token: String?
    ): Call<ResponseBody?>?
}