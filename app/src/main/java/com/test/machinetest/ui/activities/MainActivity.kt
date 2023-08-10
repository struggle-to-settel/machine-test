package com.test.machinetest.ui.activities

import android.os.Bundle
import com.example.newsalltime.R
import com.example.newsalltime.databinding.ActivityMainBinding
import com.test.machinetest.ui.fragments.SplashFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayout(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().add(R.id.flMain, SplashFragment()).commit()
    }

}