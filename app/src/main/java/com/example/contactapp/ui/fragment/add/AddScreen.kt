package com.example.contactapp.ui.fragment.add

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Parcelable
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.contactapp.R
import com.example.contactapp.base.BaseFragment
import com.example.contactapp.databinding.FragmentAddScreenBinding
import com.example.contactapp.model.ContactModel
import com.example.contactapp.ui.activities.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize


@AndroidEntryPoint
class AddScreen : BaseFragment<FragmentAddScreenBinding, MainViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_add_screen
    private val args: AddScreenArgs by navArgs()

    override val viewModel: MainViewModel by viewModels(
        ownerProducer = { requireActivity() }
    )

    companion object {
        private const val REQUEST_STORAGE_PERMISSION = 1
    }

    var avatarImage : String? = null
    private val galleryResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            val selectedImageUri: Uri? = result.data?.data
            // Use the Uri to load the image
            avatarImage = selectedImageUri.toString()
           loadImage(selectedImageUri.toString())
        }
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        setUpHomeToolbar()
        when(args.ScreenType) {
            ScreenType.UPDATE -> {
                val user = args.userModel!!
                binding.toolbar.setTitle("Update Contact")
                binding.usernameEditText.setText(user.name)
                binding.phoneEditText.setText(user.phone)
                binding.emailEditText.setText(user.email)
                avatarImage = user.avatar
                Glide.with(this)
                    .load(user.avatar)
                    .circleCrop()
                    .into(binding.imageProfile)
                binding.contactTitle.text = "Update Contact"

            }
            ScreenType.ADD -> {
                binding.contactTitle.text = "Add Contact"
                binding.toolbar.setTitle("Add Contact")
            }
        }
    }

    override fun initData() {

    }

    private fun loadImage(imageUri: String) {
        imageUri.let {
            Glide.with(this)
                .load(it)
                .circleCrop()
                .into(binding.imageProfile)  // Make sure to have an ImageView with id 'imageView' in your layout
        }
    }

    override fun initAction() {
        binding.cameraBtn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_STORAGE_PERMISSION
                )
            } else {
                openGallery()
            }
        }
        binding.submitBtn.setOnClickListener {
            when(args.ScreenType) {
                ScreenType.UPDATE -> {
                    val user = ContactModel(args.userModel!!.id,binding.usernameEditText.text.toString(), binding.phoneEditText.text.toString(), binding.emailEditText.text.toString(), avatarImage!!)
                    viewModel.updateContact(user)
                    findNavController().popBackStack()
                }
                ScreenType.ADD -> {
                    val user = ContactModel(null,binding.usernameEditText.text.toString(), binding.phoneEditText.text.toString(), binding.emailEditText.text.toString(), avatarImage!!)
                    viewModel.addContact(user)
                }
            }

        }
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryResultLauncher.launch(intent)
    }

    override fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when {
                        state.isLoading -> {

                        }
                        state.addSuccess && !state.isLoading-> {
                          findNavController().popBackStack()
                        }

                    }
                }
            }
        }
    }



    private fun setUpHomeToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_ios_new_24)
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(requireContext(),R.color.white))

    }


}

@Parcelize
enum class ScreenType : Parcelable {
    ADD,
    UPDATE
}