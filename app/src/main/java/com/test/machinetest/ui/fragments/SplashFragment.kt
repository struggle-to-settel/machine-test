package com.test.machinetest.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.newsalltime.R
import com.example.newsalltime.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override fun getLayout(): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.flMain, HomeFragment()).commit()
        }, 1500)
    }
}