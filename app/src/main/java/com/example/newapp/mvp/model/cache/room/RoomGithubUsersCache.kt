package com.example.newapp.mvp.model.cache.room

import com.example.newapp.mvp.model.cache.IGithubUsersCache
import com.example.newapp.mvp.model.entity.GithubUser
import com.example.newapp.mvp.model.entity.room.RoomGithubUser
import com.example.newapp.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubUsersCache(
    private val database: Database
): IGithubUsersCache {

    override fun getUsers() = Single.fromCallable {
        database.userDao.getAll().map { roomUser ->
            GithubUser(
                roomUser.id,
                roomUser.login,
                roomUser.avatarUrl,
                roomUser.reposUrl)
        }
    }

    override fun putUsers(users: List<GithubUser>) = Completable.fromAction {
        val roomUsers = users.map { user ->
            RoomGithubUser(
                user.id.toString(),
                user.login,
                user.avatarUrl,
                user.reposUrl
            )
        }
        database.userDao.insert(roomUsers)
    }.subscribeOn(Schedulers.io())
}