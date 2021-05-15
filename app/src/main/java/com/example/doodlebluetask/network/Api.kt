package com.example.doodlebluetask.network

import com.example.doodlebluetask.model.Assets
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("assets")
    suspend fun getCryptoCurrency(): Response<Assets>

}