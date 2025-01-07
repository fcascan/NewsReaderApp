package com.fcascan.newsreaderapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fcascan.newsreaderapp.domain.UserModel
import com.fcascan.newsreaderapp.use_cases.FetchUsersFromRemoteUseCase
import com.fcascan.newsreaderapp.use_cases.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersScreenViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val fetchUsersFromRemoteUseCase: FetchUsersFromRemoteUseCase
) : ViewModel() {
    companion object {
        private val TAG = UsersScreenViewModel::class.java.simpleName
    }

    private val _usersList = MutableStateFlow<List<UserModel>>(emptyList())
    val usersList = _usersList
    fun setUsersList(usersList: List<UserModel>) { _usersList.value = usersList }

    init {
        updateUsers()
    }

    fun updateUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val usersList = getUsersUseCase.invoke()
            setUsersList(usersList)
        }
    }

    fun fetchUsersFromRemote() {
        //TODO: Llamar cuando se tire del scroll justo en el limite superior
        viewModelScope.launch(Dispatchers.IO) {
            val usersList = fetchUsersFromRemoteUseCase.invoke()
            setUsersList(usersList)
        }
    }
}