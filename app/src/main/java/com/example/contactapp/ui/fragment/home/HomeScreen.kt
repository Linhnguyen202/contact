package com.example.contactapp.ui.fragment.home

import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.contactapp.R
import com.example.contactapp.base.BaseFragment
import com.example.contactapp.databinding.FragmentHomeScreenBinding
import com.example.contactapp.model.ContactModel
import com.example.contactapp.ui.activities.MainViewModel
import com.example.contactapp.ui.adapter.ContactAdapter
import com.example.contactapp.ui.fragment.add.ScreenType
import com.example.contactapp.utils.ResultState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeScreen : BaseFragment<FragmentHomeScreenBinding, MainViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_home_screen


    override val viewModel: MainViewModel by viewModels(
        ownerProducer = { requireActivity() }
    )

    private val contactAdapter: ContactAdapter by lazy {
        ContactAdapter(onClickItem,onClickDeleteItem)
    }

    override fun initView() {
        setHasOptionsMenu(true)
        setUpHomeToolbar()
        getData()
        binding.contactList.apply {
            adapter = contactAdapter
            clipToPadding = false
        }

    }

    override fun initData() {

    }
    private fun getData() {

        viewModel.getContact()
    }
    override fun initAction() {
        binding.addButton.setOnClickListener {
            val action = HomeScreenDirections.actionHomeScreenToAddScreen(null,ScreenType = ScreenType.ADD)
            findNavController().navigate(action)
        }
    }



    override fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when {
                        state.isLoading -> {

                        }
                        state.contacts is ResultState.Success && !state.isLoading -> {
                            contactAdapter.submitList(state.contacts.result)
                        }
                        state.addSuccess && !state.isLoading-> {
                           viewModel.getContact()

                        }
                        state.deleteSuccess && !state.isLoading-> {
                            viewModel.getContact()
                        }
                    }
                }
            }
        }


    }

    private fun setUpHomeToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.baseline_menu_24)
        binding.toolbar.setTitle("Contacts")
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(requireContext(),R.color.white))


    }

    private val onClickItem : (ContactModel) -> Unit = {
        val action = HomeScreenDirections.actionHomeScreenToDetailScreen(it)
        findNavController().navigate(action)
    }

    private val onClickDeleteItem : (Int) -> Unit = {
        viewModel.deleteContact(it)
    }




}