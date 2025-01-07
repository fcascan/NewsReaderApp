package com.fcascan.newsreaderapp.use_cases

import android.util.Log
import com.fcascan.newsreaderapp.data.local.repository.UsersLocalRepository
import com.fcascan.newsreaderapp.data.network.repository.UsersRemoteRepository
import com.fcascan.newsreaderapp.domain.UserModel
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val usersLocalRepository: UsersLocalRepository,
    private val fetchUsersFromRemoteUseCase: FetchUsersFromRemoteUseCase
) {
    companion object {
        private val TAG = GetUsersUseCase::class.java.simpleName
    }

    suspend operator fun invoke() : List<UserModel> {
        val usersLocalList = usersLocalRepository.getAll()
        Log.d(TAG, "invoke: usersNetworkList=$usersLocalList")
        if(usersLocalList.isNotEmpty()) {
            return usersLocalList
        }
        Log.i(TAG, "invoke(): usersLocalList is empty. Fetching from network...")
        return fetchUsersFromRemoteUseCase.invoke()
    }
}