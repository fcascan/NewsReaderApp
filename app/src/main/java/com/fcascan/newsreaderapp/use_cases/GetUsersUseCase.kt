package com.fcascan.newsreaderapp.use_cases

import android.util.Log
import com.fcascan.newsreaderapp.data.network.repository.UsersNetworkRepository
import com.fcascan.newsreaderapp.domain.UserModel
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UsersNetworkRepository,
) {
    companion object {
        private val TAG = GetUsersUseCase::class.java.simpleName
    }

    suspend operator fun invoke() : List<UserModel> {
        val usersNetworkList = userRepository.getUsers()
        Log.d(TAG, "invoke: usersNetworkList=$usersNetworkList")
        val usersModelList = usersNetworkList.map { it.toUserModel() }
        if (usersModelList.any { it.id == -1L } ) {
            Log.d(TAG, "invoke: usersModelList is empty")
            return emptyList()
        } else return usersModelList
    }
}