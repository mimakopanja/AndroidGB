package com.example.newapp.mvp.model.repo

import com.example.newapp.mvp.model.api.IDataSource
import com.example.newapp.mvp.model.cache.IGithubUsersCache
import com.example.newapp.mvp.model.entity.GithubRepository
import com.example.newapp.mvp.model.entity.GithubUser
import com.example.newapp.mvp.model.entity.room.RoomGithubRepository
import com.example.newapp.mvp.model.entity.room.RoomGithubUser
import com.example.newapp.mvp.model.entity.room.db.Database
import com.example.newapp.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IGithubUsersCache
): IGithubUsersRepo {
    override fun loadUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers().flatMap { users ->
                cache.putUsers(users).toSingleDefault(users)
            }
        } else {
            cache.getUsers()
        }
    }.subscribeOn(Schedulers.io())
}