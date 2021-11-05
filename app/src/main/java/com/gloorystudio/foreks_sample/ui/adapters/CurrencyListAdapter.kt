package com.gloorystudio.foreks_sample.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gloorystudio.foreks_sample.R
import com.gloorystudio.foreks_sample.data.models.CurrencyEntry
import com.gloorystudio.foreks_sample.databinding.ItemCurrencyBinding

class CurrencyListAdapter(private var currencyList:ArrayList<CurrencyEntry>)
    :RecyclerView.Adapter<CurrencyListAdapter.CurrencyListViewHolder>(){

    private var containerCardViewOnClick: ((CurrencyEntry, View) -> Unit)? = null
    fun onClickItem(actionFragmentList: (CurrencyEntry, View) -> Unit) {
        this.containerCardViewOnClick = actionFragmentList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemCurrencyBinding>(
            inflater,
            R.layout.item_currency,
            parent,
            false
        )
        return CurrencyListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyListViewHolder, position: Int)  =
        holder.bind(currencyList[position])

    override fun getItemCount(): Int = currencyList.size

    fun updateGameList(newList: ArrayList<CurrencyEntry>) {
        val diffCallBack = CurrencyListDiffCallBack(currencyList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        diffResult.dispatchUpdatesTo(this)
        this.currencyList = newList
    }

    inner class CurrencyListViewHolder(var binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.containerCardView.setOnClickListener {
                containerCardViewOnClick?.invoke(currencyList[adapterPosition], it)
            }
        }

        fun bind(currency: CurrencyEntry) {
            binding.currency = currency
        }
    }

    class CurrencyListDiffCallBack(
        private val oldCurrencyList: List<CurrencyEntry>,
        private val newCurrencyList: List<CurrencyEntry>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldCurrencyList.size
        override fun getNewListSize(): Int = newCurrencyList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldCurrencyList[oldItemPosition].id == newCurrencyList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldCurrencyList[oldItemPosition].code == newCurrencyList[newItemPosition].code &&
                    oldCurrencyList[oldItemPosition].description == newCurrencyList[newItemPosition].description &&
                    oldCurrencyList[oldItemPosition].id == newCurrencyList[newItemPosition].id &&
                    oldCurrencyList[oldItemPosition].clock == newCurrencyList[newItemPosition].clock &&
                    oldCurrencyList[oldItemPosition].last == newCurrencyList[newItemPosition].last &&
                    oldCurrencyList[oldItemPosition].pDifference == newCurrencyList[newItemPosition].pDifference &&
                    oldCurrencyList[oldItemPosition].dLow == newCurrencyList[newItemPosition].dLow &&
                    oldCurrencyList[oldItemPosition].dHigh == newCurrencyList[newItemPosition].dHigh &&
                    oldCurrencyList[oldItemPosition].buy == newCurrencyList[newItemPosition].buy &&
                    oldCurrencyList[oldItemPosition].difference == newCurrencyList[newItemPosition].difference &&
                    oldCurrencyList[oldItemPosition].sell == newCurrencyList[newItemPosition].sell &&
                    oldCurrencyList[oldItemPosition].pdClose == newCurrencyList[newItemPosition].pdClose &&
                    oldCurrencyList[oldItemPosition].isRealTimeData == newCurrencyList[newItemPosition].isRealTimeData
        }
    }
}