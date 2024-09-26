package com.example.contactapp.ui.activities

import androidx.lifecycle.viewModelScope
import com.example.contactapp.base.BaseViewModel
import com.example.contactapp.mapper.ContactMapper
import com.example.contactapp.model.ContactModel
import com.example.contactapp.utils.ResultState
import com.example.domain.use_case.AddContactUseCase
import com.example.domain.use_case.GetContactUseCase
import com.example.domain.use_case.RemoveContactUseCase
import com.example.domain.use_case.UpdateContactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val contactMapper: ContactMapper,
    private val getContactUseCase: GetContactUseCase,
    private val addContactUseCase: AddContactUseCase,
    private val updateContactUseCase: UpdateContactUseCase,
    private val deleteContactUseCase: RemoveContactUseCase
) : BaseViewModel<MainUiState>(
    MainUiState(
        isLoading = false,
        addSuccess = false,
        deleteSuccess = false,
        updateSuccess = false,
        contacts = ResultState.Uninitialised
        )
) {

    fun resetState() {
        setState {
            it.copy(
                isLoading = false,
                addSuccess = false,
                deleteSuccess = false,
                updateSuccess = false,
            )
        }
    }
    fun getContact() = viewModelScope.launch(Dispatchers.IO) {
        setState {
            it.copy(
                isLoading = true
            )
        }
        getContactUseCase.execute().collect { result ->
            if(result.isEmpty()) {
                setState {
                    it.copy(
                        isLoading = false,
                        deleteSuccess = false,
                        addSuccess = false,
                        updateSuccess = false,
                        contacts = ResultState.Empty
                    )
                }
            }
            else {
                setState {
                    it.copy(
                        isLoading = false,
                        deleteSuccess = false,
                        addSuccess = false,
                        updateSuccess = false,
                        contacts = ResultState.Success(
                            result.map {
                                contactMapper.mapDataFromDomainToUI(it)
                            }
                        )
                    )
                }
            }

        }
    }

    fun addContact(userModel: ContactModel) = viewModelScope.launch(Dispatchers.IO) {
        val result = addContactUseCase.execute(contactMapper.mapDataFromUIToDomain(userModel))
        if(result.toString().isNotEmpty()) {
            setState {
                it.copy(
                    isLoading = false,
                    addSuccess = true
                )
            }

        }
        else {
            setState {
                it.copy(
                    isLoading = false,
                    addSuccess = false
                )
            }
        }
    }

    fun deleteContact(id : Int) = viewModelScope.launch(Dispatchers.IO) {
       deleteContactUseCase.execute(id)
        setState {
            it.copy(
                isLoading = false,
                deleteSuccess = true,

            )
        }
    }

    fun updateContact(userModel: ContactModel) = viewModelScope.launch(Dispatchers.IO) {
        val result = updateContactUseCase.execute(contactMapper.mapDataFromUIToDomain(userModel))
        if(result.toString().isNotEmpty()) {
            setState {
                it.copy(
                    isLoading = false,
                    updateSuccess = true
                )
            }
        }
        else {
            setState {
                it.copy(
                    isLoading = false,
                    updateSuccess = false
                )
            }
        }
    }
}