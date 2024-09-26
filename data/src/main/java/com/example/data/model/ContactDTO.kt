package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContactDTO (
    @PrimaryKey(autoGenerate = true)
    val id : Int?,
    val name : String,
    val phone : String,
    val email : String,
    val avatar : String
)