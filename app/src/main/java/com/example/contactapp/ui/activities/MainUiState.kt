package com.example.contactapp.ui.activities

import com.example.contactapp.model.ContactModel
import com.example.contactapp.utils.ResultState

data class MainUiState (
    val isLoading : Boolean,
    val addSuccess : Boolean,
    val deleteSuccess : Boolean,
    val updateSuccess: Boolean,
    val contacts : ResultState<List<ContactModel>>
)