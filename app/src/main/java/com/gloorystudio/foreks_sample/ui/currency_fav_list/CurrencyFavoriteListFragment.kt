package com.gloorystudio.foreks_sample.ui.currency_fav_list

import android.os.CountDownTimer
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gloorystudio.foreks_sample.R
import com.gloorystudio.foreks_sample.base.BaseFragment
import com.gloorystudio.foreks_sample.data.models.CurrencyEntry
import com.gloorystudio.foreks_sample.databinding.FragmentCurrencyFavoriteListBinding
import com.gloorystudio.foreks_sample.ui.adapters.CurrencyListAdapter
import com.gloorystudio.foreks_sample.ui.currency_list.CurrencyListFragmentDirections
import com.gloorystudio.foreks_sample.util.AutoCompleteItem
import com.gloorystudio.foreks_sample.util.Constants
import com.gloorystudio.foreks_sample.util.navigate

class CurrencyFavoriteListFragment :
    BaseFragment<FragmentCurrencyFavoriteListBinding>(R.layout.fragment_currency_favorite_list) {

    private val viewModel: CurrencyFavoriteListViewModel by viewModels {
        viewModelFactory
    }
    private var currencyListAdapter = CurrencyListAdapter(arrayListOf())
    lateinit var timer: CountDownTimer

    override fun initUi() {
        initRecyclerView()
        initAutoCompleteTvs()
        currencyListAdapter.onClickItem { currencyEntry, view ->
            CurrencyFavoriteListFragmentDirections.actionCurrencyFavoriteListFragmentToCurrencyDetailFragment(
                currencyEntry.code
            ).navigate(view)
        }
    }

    private fun initRecyclerView() {
        binding.rvCurrencyList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCurrencyList.adapter = currencyListAdapter
    }

    private fun initAutoCompleteTvs() {
        var autoCompleteTvAdapter = ArrayAdapter(
            requireContext(), R.layout.item_auto_complete,
            AutoCompleteItem.getItemList()
        )
        binding.autoCompleteTvFirstField.setText(AutoCompleteItem.LAST.text)
        binding.autoCompleteTvFirstField.setAdapter(autoCompleteTvAdapter)
        binding.autoCompleteTvFirstField.doOnTextChanged { text, _, _, _ ->
            currencyListAdapter.firstAutoCompleteItem = AutoCompleteItem.getItem(text.toString())
        }

        binding.autoCompleteTvSecondField.setText(AutoCompleteItem.PDIFFERENCE.text)
        binding.autoCompleteTvSecondField.setAdapter(autoCompleteTvAdapter)
        binding.autoCompleteTvSecondField.doOnTextChanged { text, _, _, _ ->
            currencyListAdapter.secondAutoCompleteItem = AutoCompleteItem.getItem(text.toString())
        }
    }

    override fun observeData() {
        viewModel.currencyList.observe(viewLifecycleOwner, { currencyList ->
            if (currencyList.isNotEmpty()) {
                currencyListAdapter.updateGameList(currencyList as ArrayList<CurrencyEntry>)
            }
        })
    }

    override fun onStart() {
        super.onStart()
        timerStart()
    }

    override fun onStop() {
        super.onStop()
        timer.cancel()
    }

    private fun timerStart() {
        timer = object : CountDownTimer(Long.MAX_VALUE, Constants.DEFAULT_REFRESH_TIME) {
            override fun onFinish() {
                timer.start()
            }

            override fun onTick(millisUntilFinished: Long) {
                viewModel.fetchCurrencyList()
            }
        }.start()
    }
}
