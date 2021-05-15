package com.example.doodlebluetask.model

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(
    tableName = "asset_table"
)
data class abc(
    @PrimaryKey(autoGenerate = true)
    val assetId:Int,

    @ColumnInfo(name = "id")
    val id: String= "" ,

    @ColumnInfo(name = "rank")
    val rank: String= "",

    @ColumnInfo(name = "symbol")
    val symbol: String = "",

    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "supply")
    val supply: String = "",

    /* @ColumnInfo(name = "max_supply")
     val maxSupply: String = "",*/

    @ColumnInfo(name = "market_cap_usd")
    val marketCapUsd: String = "",

    @ColumnInfo(name = "volumeUsd24Hr")
    val volumeUsd24Hr: String = "",

    @ColumnInfo(name = "priceUsd")
    val priceUsd: String = "",

    @ColumnInfo(name = "changePercent24Hr")
    val changePercent24Hr: String = "",

    @ColumnInfo(name = "vwap24Hr")
    val vwap24Hr: String = "",

    @ColumnInfo(name = "explorer")
    val explorer: String = "",
)
