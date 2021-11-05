package com.gloorystudio.foreks_sample.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gloorystudio.foreks_sample.R
import com.gloorystudio.foreks_sample.data.models.CurrencyDetailDataEntry
import com.gloorystudio.foreks_sample.databinding.ItemCurrencyDetailDataBinding

class CurrencyDetailDataAdapter(private var currencyDataList: ArrayList<CurrencyDetailDataEntry>) :
    RecyclerView.Adapter<CurrencyDetailDataAdapter.CurrencyDetailDataViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyDetailDataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemCurrencyDetailDataBinding>(
            inflater,
            R.layout.item_currency_detail_data,
            parent,
            false
        )
        return CurrencyDetailDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyDetailDataViewHolder, position: Int) =
        holder.bind(currencyDataList[position])

    override fun getItemCount(): Int = currencyDataList.size

    fun updateGameList(newList: ArrayList<CurrencyDetailDataEntry>) {
        val diffCallBack = CurrencyDetailDataDiffCallBack(currencyDataList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        diffResult.dispatchUpdatesTo(this)
        this.currencyDataList = newList
    }

    inner class CurrencyDetailDataViewHolder(var binding: ItemCurrencyDetailDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currencyDetailDataEntry: CurrencyDetailDataEntry) {
            binding.currencyDetailDataEntry = currencyDetailDataEntry
        }
    }

    class CurrencyDetailDataDiffCallBack(
        private val oldCurrencyDataList: List<CurrencyDetailDataEntry>,
        private val newCurrencyDataList: List<CurrencyDetailDataEntry>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldCurrencyDataList.size
        override fun getNewListSize(): Int = newCurrencyDataList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldCurrencyDataList[oldItemPosition].title == newCurrencyDataList[newItemPosition].title
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldCurrencyDataList[oldItemPosition].title == newCurrencyDataList[newItemPosition].title &&
                    oldCurrencyDataList[oldItemPosition].data == newCurrencyDataList[newItemPosition].data
        }
    }
}