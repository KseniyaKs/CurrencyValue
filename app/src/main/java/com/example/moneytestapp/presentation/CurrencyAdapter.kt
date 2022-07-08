package com.example.moneytestapp.presentation

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneytestapp.R
import com.example.moneytestapp.domain.CurrencyModel
import kotlinx.coroutines.flow.StateFlow


class CurrencyAdapter(
    val onLikePressed: OnItemClickListener,
) : RecyclerView.Adapter<CurrencyViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(currency: CurrencyModel)
    }

    private val currencyList: MutableList<CurrencyModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
        return CurrencyViewHolder(view, onLikePressed)
    }


    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currencyList[position])
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    fun setItems(list: List<CurrencyModel>) {
        currencyList.clear()
        currencyList.addAll(list)
        notifyDataSetChanged()
    }

    fun updateItem(item: CurrencyModel) {
        val element = currencyList.find {
            it.code == item.code
        }
        notifyItemChanged(currencyList.indexOf(element))
    }

}


