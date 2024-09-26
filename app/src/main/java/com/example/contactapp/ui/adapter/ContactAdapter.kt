package com.example.contactapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contactapp.databinding.ContactItemBinding
import com.example.contactapp.model.ContactModel

class ContactAdapter(
    private val onClick : (ContactModel) -> Unit,
    private val onRemove : (Int) -> Unit
) : ListAdapter<ContactModel, ContactAdapter.ContactViewHolder>(ContactModel.DIFF) {
    class ContactViewHolder(private val binding : ContactItemBinding,
                            private val onClick : (ContactModel) -> Unit,
                            private val onRemove : (Int) -> Unit
        ) : RecyclerView.ViewHolder(binding.root) {
        fun inject(userModel: ContactModel) {
            itemView.setOnClickListener {
                onClick.invoke(userModel)
            }
            binding.deleteButton.setOnClickListener {
                onRemove.invoke(userModel.id!!)
            }
            binding.name.text = userModel.name
            Glide.with(itemView)
                .load(userModel.avatar)
                .circleCrop()
                .into(binding.profileImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            ContactItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick,
            onRemove
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.inject(getItem(position))
    }
}

