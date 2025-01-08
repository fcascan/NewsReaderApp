package com.fcascan.newsreaderapp.data.local.repository

import com.fcascan.newsreaderapp.data.local.dao.UsersDao
import com.fcascan.newsreaderapp.domain.UserModel
import javax.inject.Inject

class UsersLocalRepositoryImpl @Inject constructor(
    private val usersDao: UsersDao,
) : UsersLocalRepository {
    override suspend fun getAll(): List<UserModel> = usersDao.getAll().map { it.toUserModel() }

    override suspend fun getById(userId: Long): UserModel? {
        val user = usersDao.getById(userId)
        return user?.toUserModel()
    }

    override suspend fun insertList(users: List<UserModel>) {
        val usersLocalEntity = users.map { it.toUserLocalEntity() }
        usersDao.insertList(usersLocalEntity)
    }

    override suspend fun deleteAll() {
        usersDao.deleteAll()
    }
}