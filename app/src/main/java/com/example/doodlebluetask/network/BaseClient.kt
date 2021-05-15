package com.example.doodlebluetask.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BaseClient {

    private val BaseUrl: String = "https://api.coincap.io/v2/";

   public val getInstance: Api by lazy {

        val retrofit =
            Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create())
                .build()
        retrofit.create(Api::class.java)
    }

}