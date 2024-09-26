package com.example.contactapp.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactModel (
    val id : Int?,
    val name : String,
    val phone : String,
    val email : String,
    val avatar : String
): Parcelable {
    companion object {

        val DIFF = object : DiffUtil.ItemCallback<ContactModel>() {
            override fun areItemsTheSame(oldItem: ContactModel, newItem: ContactModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ContactModel, newItem: ContactModel): Boolean {
                return oldItem == newItem
            }


        }
    }
}