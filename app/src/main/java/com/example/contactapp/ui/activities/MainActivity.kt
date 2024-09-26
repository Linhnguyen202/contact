package com.example.contactapp.ui.activities

import androidx.activity.viewModels
import com.example.contactapp.R
import com.example.contactapp.base.BaseActivity
import com.example.contactapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun layoutRes(): Int = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    override fun initView() {

    }

}