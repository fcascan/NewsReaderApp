package com.fcascan.newsreaderapp.use_cases

import android.util.Log
import com.fcascan.newsreaderapp.data.local.repository.UsersLocalRepository
import com.fcascan.newsreaderapp.data.network.repository.UsersRemoteRepository
import com.fcascan.newsreaderapp.domain.UserModel
import javax.inject.Inject

class FetchUsersFromRemoteUseCase @Inject constructor(
    private val usersLocalRepository: UsersLocalRepository,
    private val usersRemoteRepository: UsersRemoteRepository
) {
    companion object {
        private val TAG = FetchUsersFromRemoteUseCase::class.java.simpleName
    }

    suspend operator fun invoke() : List<UserModel> {
        val usersRemoteList = usersRemoteRepository.getAll()
        if (usersRemoteList.any { it.id == -1L } ) {
            Log.d(TAG, "invoke(): usersRemoteList is empty")
            return emptyList()
        } else {
            Log.d(TAG, "invoke(): usersRemoteList=$usersRemoteList")
            usersLocalRepository.deleteAll()
            usersLocalRepository.insertList(usersRemoteList)
            return usersRemoteList
        }
    }
}