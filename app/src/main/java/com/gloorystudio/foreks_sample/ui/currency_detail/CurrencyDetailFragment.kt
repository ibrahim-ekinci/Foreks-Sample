package com.gloorystudio.foreks_sample.ui.currency_detail

import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.gloorystudio.foreks_sample.R
import com.gloorystudio.foreks_sample.base.BaseFragment
import com.gloorystudio.foreks_sample.data.models.CurrencyDetailDataEntry
import com.gloorystudio.foreks_sample.data.models.CurrencyDetailEntry
import com.gloorystudio.foreks_sample.data.remote.responses.CurrencyGraph
import com.gloorystudio.foreks_sample.databinding.FragmentCurrencyDetailBinding
import com.gloorystudio.foreks_sample.ui.adapters.CurrencyDetailDataAdapter
import com.gloorystudio.foreks_sample.util.Constants

class CurrencyDetailFragment :
    BaseFragment<FragmentCurrencyDetailBinding>(R.layout.fragment_currency_detail) {

    private val viewModel: CurrencyDetailViewModel by viewModels {
        viewModelFactory
    }
    private var currencyDetailDataAdapter = CurrencyDetailDataAdapter(arrayListOf())
    lateinit var timer: CountDownTimer
    lateinit var code: String
    lateinit var lineDataSet: LineDataSet
    lateinit var lineData: LineData
    private var isLiked = false

    override fun initUi() {
        initRecyclerView()
        arguments?.let {
            val myArgs = CurrencyDetailFragmentArgs.fromBundle(it)
            code = myArgs.code
            viewModel.getFavoriteCurrency(code){ isLiked ->
                this.isLiked = isLiked
                if (isLiked) {
                    binding.btnFavorite.text = getString(R.string.remove_favorite)
                } else {
                    binding.btnFavorite.text = getString(R.string.add_favorite)
                }
            }
            binding.btnFavorite.setOnClickListener {
                isLiked = if (isLiked) {
                    viewModel.removeFavoriteCurrency(code)
                    binding.btnFavorite.text = getString(R.string.add_favorite)
                    false
                } else {
                    viewModel.addCurrencyFavorite()
                    binding.btnFavorite.text = getString(R.string.remove_favorite)
                    true
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.rvCurrencyDataList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCurrencyDataList.adapter = currencyDetailDataAdapter
    }

    override fun observeData() {
        viewModel.currencyDetail.observe(viewLifecycleOwner, { currencyDetail ->
            currencyDetail?.let {
                binding.currencyDetail = it
                setAdapterData(it)
            }
        })

        viewModel.currencyGraph.observe(viewLifecycleOwner, { currencyGraph ->
            if (currencyGraph.isNotEmpty()) {
                binding.lineChart.visibility = View.VISIBLE
                setGraphData(currencyGraph)
            } else binding.lineChart.visibility = View.GONE
        })
    }

    private fun setGraphData(currencyGraph: CurrencyGraph) {
        val lineList = ArrayList<Entry>()
        var count = 0f
        currencyGraph.forEach {
            lineList.add(
                Entry(
                    count++,
                    it.c
                )
            )
        }
        lineDataSet = LineDataSet(lineList, code)
        lineData = LineData(lineDataSet)
        binding.lineChart.data = lineData
        lineDataSet.setDrawFilled(true)
    }

    private fun setAdapterData(currencyDetailEntry: CurrencyDetailEntry) {
        val dataList = ArrayList<CurrencyDetailDataEntry>()
        dataList.add(CurrencyDetailDataEntry("Son", currencyDetailEntry.last))
        dataList.add(CurrencyDetailDataEntry("Alış", currencyDetailEntry.buy))
        dataList.add(CurrencyDetailDataEntry("Satış", currencyDetailEntry.sell))
        dataList.add(CurrencyDetailDataEntry("Düşük", currencyDetailEntry.low))
        dataList.add(CurrencyDetailDataEntry("Yüksek", currencyDetailEntry.high))
        dataList.add(CurrencyDetailDataEntry("Fark", currencyDetailEntry.difference))
        dataList.add(CurrencyDetailDataEntry("%Fark", "%${currencyDetailEntry.pDifference}"))
        currencyDetailDataAdapter.updateGameList(dataList)
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
                viewModel.fetchCurrencyDetail(code)
                viewModel.fetchCurrencyGraph(code)
            }
        }.start()
    }
}
