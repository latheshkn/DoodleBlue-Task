package com.example.doodlebluetask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doodlebluetask.databinding.PriceRecyclerviewItemListBinding
import com.example.doodlebluetask.model.AssetsResponse

class PriceRecyclerViewAdapter: RecyclerView.Adapter<PriceRecyclerViewAdapter.Vhholder>() {

//    private  var responseList = ArrayList<AssetsResponse>()
    var responseList = mutableListOf<AssetsResponse>()
    var count: Int = 0

    fun setAssetsResponseList(list: List<AssetsResponse>) {
        responseList.clear()
        responseList.addAll(list)
        notifyDataSetChanged()

    }


    class Vhholder(binding: PriceRecyclerviewItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var txtName = binding.txtName
        var txtPrice = binding.txtPrice
        var txtChange = binding.txtChange
        var circleImageTextView = binding.circleImage
        var txtSerialNo = binding.txtSerialNo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vhholder {

        val binding = PriceRecyclerviewItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return Vhholder(binding)
    }

    override fun onBindViewHolder(holder: Vhholder, position: Int) {
        holder.txtName.text = responseList.get(position).name
        holder.txtPrice.text = "$ ${responseList.get(position).priceUsd}"
        holder.txtChange.text ="${responseList.get(position).changePercent24Hr} %"
        holder.circleImageTextView.text=responseList.get(position).name
        holder.txtSerialNo.text=responseList.get(position).name

        holder.txtSerialNo.text = responseList.get(position).assetId.toString()
}

override fun getItemCount(): Int {
    return responseList.size
}

    public fun filterList(filterList: ArrayList<AssetsResponse>) {
        responseList = filterList

        notifyDataSetChanged()
    }

}