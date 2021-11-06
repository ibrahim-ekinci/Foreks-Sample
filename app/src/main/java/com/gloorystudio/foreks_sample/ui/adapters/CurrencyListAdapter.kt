package com.gloorystudio.foreks_sample.ui.adapters

import android.graphics.Color
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.color
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gloorystudio.foreks_sample.R
import com.gloorystudio.foreks_sample.data.models.CurrencyEntry
import com.gloorystudio.foreks_sample.databinding.ItemCurrencyBinding
import com.gloorystudio.foreks_sample.util.AutoCompleteItem

class CurrencyListAdapter(private var currencyList: ArrayList<CurrencyEntry>) :
    RecyclerView.Adapter<CurrencyListAdapter.CurrencyListViewHolder>() {

    private var containerCardViewOnClick: ((CurrencyEntry, View) -> Unit)? = null
    fun onClickItem(actionFragmentList: (CurrencyEntry, View) -> Unit) {
        this.containerCardViewOnClick = actionFragmentList
    }

    var firstAutoCompleteItem = AutoCompleteItem.LAST
        set(value) {
            notifyDataSetChanged()
            field = value
        }
    var secondAutoCompleteItem = AutoCompleteItem.PDIFFERENCE
        set(value) {
            notifyDataSetChanged()
            field = value
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

    override fun onBindViewHolder(holder: CurrencyListViewHolder, position: Int) =
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
            binding.firstData = getDataByAutoComplete(firstAutoCompleteItem, currency)
            binding.secondData = getDataByAutoComplete(secondAutoCompleteItem, currency)
        }

        private fun getDataByAutoComplete(
            autoCompType: AutoCompleteItem,
            currency: CurrencyEntry
        ) = when (autoCompType) {
            AutoCompleteItem.LAST -> {
                SpannableStringBuilder().append(currency.last)
            }
            AutoCompleteItem.PDIFFERENCE -> {
                val difference = currency.pDifference.replace(",", ".").toFloat()
                val color = when {
                    difference > 0 -> Color.GREEN
                    difference < 0 -> Color.RED
                    else -> Color.GRAY
                }
                SpannableStringBuilder()
                    .color(color) { append("%${currency.pDifference}") }
            }
            AutoCompleteItem.DIFFERENCE -> {
                val difference = currency.difference.replace(",", ".").toFloat()
                val color = when {
                    difference > 0 -> Color.GREEN
                    difference < 0 -> Color.RED
                    else -> Color.GRAY
                }
                SpannableStringBuilder()
                    .color(color){
                        append(currency.difference)
                    }
            }
            AutoCompleteItem.LOW -> {
                SpannableStringBuilder().append(currency.dLow)
            }
            AutoCompleteItem.HIGH -> {
                SpannableStringBuilder().append(currency.dHigh)
            }
            AutoCompleteItem.BUY -> {
                SpannableStringBuilder().append(currency.buy)
            }
            AutoCompleteItem.SELL -> {
                SpannableStringBuilder().append(currency.sell)
            }
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