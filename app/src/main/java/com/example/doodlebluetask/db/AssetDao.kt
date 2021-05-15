package com.example.doodlebluetask.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.doodlebluetask.model.AssetsResponse

@Dao
interface AssetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllAsset(assetsResponse: List<AssetsResponse>)

    @Query("SELECT * FROM asset_table")
    // this time it will not be suspend function bcz it will return LiveData object which doesnot work it suspend function
    fun getAllAssets(): LiveData<List<AssetsResponse>>

    @Query("DELETE FROM asset_table")
    suspend fun deleteAll()
}