package com.example.contactapp.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.contactapp.R

abstract class BaseActivity<T : ViewDataBinding, M : BaseViewModel<*>> : AppCompatActivity() {
    private lateinit var _binding: T
    val binding get() = _binding
    @LayoutRes
    protected abstract fun layoutRes(): Int
    protected abstract val viewModel : M
    protected abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        _binding = DataBindingUtil.setContentView(this, layoutRes());
        binding.lifecycleOwner = this
        initView()
    }


}