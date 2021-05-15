package com.example.doodlebluetask.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doodlebluetask.model.Assets
import com.example.doodlebluetask.model.AssetsResponse
import com.example.doodlebluetask.repository.CryptoCurrencyRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class CryptoCurrencyViewModel(private val repository: CryptoCurrencyRepository) : ViewModel() {

    private val responseLiveData = MutableLiveData<Response<Assets>>()
    private val filterLiveData = MutableLiveData<ArrayList<AssetsResponse>>()
    var getlocatDbLiveData:LiveData<List<AssetsResponse>> = getAllAssets()


    fun getFilterLiveData(): LiveData<ArrayList<AssetsResponse>> {
        return filterLiveData
    }


    fun getCryptoCurrency() {
        // viewModelScope will make sure this coroutine will be alive till the time our viewModelScope is alive
        viewModelScope.launch {

            val response = repository.getCryptoCurrency()
            responseLiveData.postValue(response)

            addAllAsset(response.body()!!.data)
        }
    }

    fun addAllAsset(assetsResponse: List<AssetsResponse>) {
        // viewModelScope will make sure this coroutine will be alive till the time our viewModelScope is alive
        viewModelScope.launch {
            repository.addAllAsset(assetsResponse)

            Log.d("abc", "Data Inserted")
        }
    }

    fun getAllAssets(): LiveData<List<AssetsResponse>> {
        Log.i("abc", "getAllAsset From Db")
        return repository.getAllAssets()
    }


    fun getFilterData(searchTxt: String, list: List<AssetsResponse>) {
        var filterList: ArrayList<AssetsResponse> = ArrayList()

        list.forEach {
            if (it.name.toLowerCase().contains(searchTxt.toLowerCase())) {
                filterList.add(it)
                filterLiveData.value = filterList
            }
        }
    }
}

