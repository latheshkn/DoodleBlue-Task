package com.example.doodlebluetask.ui.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.doodlebluetask.R
import com.example.doodlebluetask.adapter.PriceRecyclerViewAdapter
import com.example.doodlebluetask.databinding.FragmentPriceBinding
import com.example.doodlebluetask.db.AssetDatabase
import com.example.doodlebluetask.model.AssetsResponse
import com.example.doodlebluetask.network.BaseClient
import com.example.doodlebluetask.repository.CryptoCurrencyRepository
import com.example.doodlebluetask.uitl.isConnectedToNetwork
import com.example.doodlebluetask.viewmodel.CryptoCurrencyViewModel
import com.example.doodlebluetask.viewmodel.ViewModelFactory


class PriceFragment : Fragment() {

    val priceRecyclerViewAdapter = PriceRecyclerViewAdapter()
    var list: List<AssetsResponse> = ArrayList()
    lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentPriceBinding
    lateinit var viewModel: CryptoCurrencyViewModel
    lateinit var searchView: EditText

    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var mContext: Context

    fun initialize() {
        mContext = requireContext()

        val retrofitService = BaseClient.getInstance
        val db = AssetDatabase(mContext)
        swipeRefreshLayout = binding.swipeRefreshLayout
        searchView = binding.searchView


        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(CryptoCurrencyRepository(db, retrofitService))
        ).get(CryptoCurrencyViewModel::class.java)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = priceRecyclerViewAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPriceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("abc", "onCreate")

        initialize()

        swipeRefresh()

        getAssetObserver()

        getFilterObserver()

        searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.getFilterData(s.toString(), list)
            }

        })

    }

    private fun getFilterObserver() {
        viewModel.getFilterLiveData().observe(viewLifecycleOwner, {
            priceRecyclerViewAdapter.setAssetsResponseList(it)
        })
    }

    private fun getAssetObserver() {
        viewModel.getlocatDbLiveData.observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                checkInternetFetchData()
            }
            list = it
            priceRecyclerViewAdapter.setAssetsResponseList(it)

            Log.i("abc", "recived data from viemodel")
        })

    }

    private fun swipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                priceRecyclerViewAdapter.notifyDataSetChanged()
                getAssetObserver()
                swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    private fun checkInternetFetchData() {

        if (mContext.isConnectedToNetwork()) {
            if (list.isEmpty()) {
                viewModel.getCryptoCurrency()
            } else {
                Toast.makeText(mContext, "Data Already Present", Toast.LENGTH_SHORT).show()
            }


        } else {
            Toast.makeText(requireContext(), "Not internet", Toast.LENGTH_LONG).show()
        }
    }
}