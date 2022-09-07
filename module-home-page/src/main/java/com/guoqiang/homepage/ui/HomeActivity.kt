package com.guoqiang.homepage.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.guoqiang.homepage.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, HomeFragment.newInstance()).commit()
    }
}