package com.gloorystudio.foreks_sample.util

enum class AutoCompleteItem(val text: String) {
    LAST("Son"),
    PDIFFERENCE("%Fark"),
    DIFFERENCE("Fark"),
    LOW("G. Düşük"),
    HIGH("G. Yüksek"),
    BUY("Alış"),
    SELL("Satış");

    companion object {
        fun getItemList(): ArrayList<String> = arrayListOf(
            LAST.text,
            PDIFFERENCE.text,
            DIFFERENCE.text,
            LOW.text,
            HIGH.text,
            BUY.text,
            SELL.text
        )

        fun getItem(text: String) = AutoCompleteItem.values().first { it.text == text }
    }
}