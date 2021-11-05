package com.gloorystudio.foreks_sample.ui.adapters

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.gloorystudio.foreks_sample.R
import timber.log.Timber

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("setTextWithColor")
    fun bindSetTextWithColor(view: TextView, text: String?) {
        text?.let {
            try {
                val difference = it.replace(",", ".").toFloat()
                val color = when {
                    difference > 0 -> Color.GREEN
                    difference < 0 -> Color.RED
                    else -> Color.GRAY
                }
                view.setTextColor(color)
            } catch (e: Exception) {
                Timber.d(e)
            }
            view.text = "%${text}"
        }
    }

    @JvmStatic
    @BindingAdapter("sell", "last")
    fun bindUrlImage(view: ImageView, sell: String?, last: String?) {
        sell?.let {
            last?.let {
                val sellD = sell.replace(".", "").replace(",", ".").toDouble()
                val lastD = last.replace(".", "").replace(",", ".").toDouble()
                val img = when {
                    sellD > lastD -> R.drawable.ic_arrow_upward
                    sellD < lastD -> R.drawable.ic_arrow_downward
                    else -> R.drawable.ic_stable
                }
                view.setImageResource(img)
            }
        }
    }
}