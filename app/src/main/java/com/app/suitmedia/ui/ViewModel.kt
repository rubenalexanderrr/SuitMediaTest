package com.app.suitmedia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {
    private val _selectedUser = MutableLiveData<String>()
    val selectedUser: LiveData<String> = _selectedUser


    fun setSelectedUser(user: String) {
        _selectedUser.value = user
    }
}