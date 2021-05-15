package com.example.doodlebluetask.repository

import androidx.lifecycle.LiveData
import com.example.doodlebluetask.db.AssetDatabase
import com.example.doodlebluetask.model.AssetsResponse
import com.example.doodlebluetask.network.Api
import com.example.doodlebluetask.network.BaseClient

class CryptoCurrencyRepository(val db:AssetDatabase,val api: Api) {

    suspend fun getCryptoCurrency() =api.getCryptoCurrency()

    suspend fun addAllAsset(assetsResponse: List<AssetsResponse>){
        db.getAssetDao().addAllAsset(assetsResponse)
    }

    fun getAllAssets(): LiveData<List<AssetsResponse>> = db.getAssetDao().getAllAssets()


}