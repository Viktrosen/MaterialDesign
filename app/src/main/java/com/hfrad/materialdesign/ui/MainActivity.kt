package com.hfrad.materialdesign.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hfrad.materialdesign.R
import com.hfrad.materialdesign.ui.picture.PictureOfTheDayFragment

open class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitAllowingStateLoss()
        }
    }
}
