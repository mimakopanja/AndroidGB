package com.example.newapp.mvp.model.repo

import com.example.newapp.mvp.model.api.IDataSource
import com.example.newapp.mvp.model.entity.GithubUser
import com.example.newapp.mvp.model.entity.room.RoomGithubUser
import com.example.newapp.mvp.model.entity.room.db.Database
import com.example.newapp.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepo(
    private val api: IDataSource,
    private val database: Database,
    private val networkStatus: INetworkStatus
): IGithubUsersRepo {
    override fun loadUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline){
            api.getUsers().flatMap { users ->
                Single.fromCallable {
                    val roomUsers = users.map { user ->
                        RoomGithubUser(
                            user.id.toString(),
                            user.login,
                            user.avatarUrl,
                            user.reposUrl
                        )
                    }
                    database.userDao.insert(roomUsers)
                    users
                }
            }
        } else {
            Single.fromCallable {
                database.userDao.getAll().map { roomUser ->
                    GithubUser(
                        roomUser.id,
                        roomUser.login,
                        roomUser.avatarUrl,
                        roomUser.reposUrl
                    )
                }
            }
        }
    }.subscribeOn(Schedulers.io())
}