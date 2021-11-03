package com.gloorystudio.foreks_sample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gloorystudio.foreks_sample.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_ForeksSample)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}