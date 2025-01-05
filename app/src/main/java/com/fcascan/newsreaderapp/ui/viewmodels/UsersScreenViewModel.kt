package com.fcascan.newsreaderapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.fcascan.newsreaderapp.models.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class UsersScreenViewModel @Inject constructor(

) : ViewModel() {
    companion object {
        private val TAG = UsersScreenViewModel::class.java.simpleName
    }

    private val _usersList = MutableStateFlow<List<UserModel>>(emptyList())
    val usersList = _usersList
    fun setUsersList(usersList: List<UserModel>) { _usersList.value = usersList }

    init {
        val mockUsersList = listOf(
            UserModel(id = 1L, "John", "Doe", ""),
            UserModel(id = 2L, "Jane", "Doe", ""),
            UserModel(id = 3L, "Alice", "Smith", ""),
            UserModel(id = 4L, "Bob", "Smith", ""),
            UserModel(id = 5L, "Charlie", "Brown", ""),
            UserModel(id = 6L, "Daisy", "Brown", ""),
            UserModel(id = 7L, "Eve", "Johnson", ""),
            UserModel(id = 8L, "Frank", "Johnson", ""),
        )
        setUsersList(mockUsersList)
    }
}