package com.example.moneytestapp.presentation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneytestapp.R
import com.example.moneytestapp.domain.CurrencyModel

class CurrencyViewHolder(itemView: View, val onLikePressed: CurrencyAdapter.OnItemClickListener,) : RecyclerView.ViewHolder(itemView) {

    val currencyName = itemView.findViewById<TextView>(R.id.currencyName)
    val currencyValue = itemView.findViewById<TextView>(R.id.currencyValue)
    val like = itemView.findViewById<ImageView>(R.id.like)

    fun bind(currency: CurrencyModel) {

        currencyName.text = currency.code
        currencyValue.text = currency.value.toString()
        like.setOnClickListener {
            onLikePressed.onItemClick(currency)
        }

        if (currency.isLike) {
            like.setImageResource(R.drawable.ic_likes_fill)
        } else like.setImageResource(R.drawable.ic_likes_empty)
    }
}