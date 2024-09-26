package com.example.contactapp.ui.fragment.detail

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.contactapp.R
import com.example.contactapp.base.BaseFragment
import com.example.contactapp.databinding.FragmentDetailScreenBinding
import com.example.contactapp.ui.activities.MainViewModel
import com.example.contactapp.ui.fragment.add.ScreenType
import com.example.contactapp.ui.fragment.home.HomeScreenDirections
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailScreen : BaseFragment<FragmentDetailScreenBinding, MainViewModel>() {

    private val args: DetailScreenArgs by navArgs()
    override fun layoutRes(): Int = R.layout.fragment_detail_screen
    override val viewModel: MainViewModel by viewModels(
        ownerProducer = { requireActivity() }
    )
    companion object {
        const val REQUEST_CALL_PERMISSION = 1
    }
    override fun initView() {
        setUpHomeToolbar()
        val user = args.UserModel
        binding.detailName.text = user.name
        binding.detailNumber.text = user.phone
        binding.detailEmail.text = user.email
        Glide.with(this)
            .load(user.avatar)
            .circleCrop()
            .into(binding.detailAva)
    }

    override fun initData() {

    }

    override fun initAction() {
        binding.editButton.setOnClickListener {
            val action = DetailScreenDirections.actionDetailScreenToAddScreen(args.UserModel, ScreenType.UPDATE )
            findNavController().navigate(action)
        }
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.callBtn.setOnClickListener {
            val number = binding.detailNumber.text
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$number")
            }
            startActivity(intent)
        }
    }

    override fun observeData() {

    }





    private fun setUpHomeToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_ios_new_24)
        binding.toolbar.setTitle("Add Contacts")
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(requireContext(),R.color.white))
    }
}