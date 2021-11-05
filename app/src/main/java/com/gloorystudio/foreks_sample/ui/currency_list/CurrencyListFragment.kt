package com.gloorystudio.foreks_sample.ui.currency_list

import android.os.CountDownTimer
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gloorystudio.foreks_sample.R
import com.gloorystudio.foreks_sample.base.BaseFragment
import com.gloorystudio.foreks_sample.data.models.CurrencyEntry
import com.gloorystudio.foreks_sample.databinding.FragmentCurrencyListBinding
import com.gloorystudio.foreks_sample.ui.adapters.CurrencyListAdapter
import com.gloorystudio.foreks_sample.util.Constants
import com.gloorystudio.foreks_sample.util.navigate

class CurrencyListFragment :
    BaseFragment<FragmentCurrencyListBinding>(R.layout.fragment_currency_list) {

    private val viewModel: CurrencyListViewModel by viewModels {
        viewModelFactory
    }
    private var currencyListAdapter = CurrencyListAdapter(arrayListOf())
    lateinit var timer: CountDownTimer

    override fun initUi() {
        initRecyclerView()
        currencyListAdapter.onClickItem { currencyEntry, view ->
            CurrencyListFragmentDirections.actionCurrencyListFragmentToCurrencyDetailFragment(
                currencyEntry.code
            ).navigate(view)
        }
    }

    private fun initRecyclerView() {
        binding.rvCurrencyList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCurrencyList.adapter = currencyListAdapter
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