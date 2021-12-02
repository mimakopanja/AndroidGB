package com.example.newapp.mvp.model.cache.room

import com.example.newapp.mvp.model.cache.IGithubRepositoriesCache
import com.example.newapp.mvp.model.entity.GithubRepository
import com.example.newapp.mvp.model.entity.GithubUser
import com.example.newapp.mvp.model.entity.room.RoomGithubRepository
import com.example.newapp.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubRepositoriesCache(
    private val database: Database
): IGithubRepositoriesCache {
    override fun getUserRepos(user: GithubUser) = Single.fromCallable {
        val roomUser = database.userDao.findByLogin(user.login) ?: throw RuntimeException("No such user in cache!")
        return@fromCallable database.repositoryDao.findById(roomUser.id)
            .map {
                GithubRepository(
                    it.id,
                    it.name,
                    it.description,
                    it.forks,
                    it.createdAt,
                    it.language,
                    it.userId
                )
            }
    }.subscribeOn(Schedulers.io())

    override fun putUserRepos(user: GithubUser, repositories: List<GithubRepository>) = Completable.fromAction {
        val roomUser = database.userDao.findByLogin(user.login) ?: throw RuntimeException("No such user in cache!")
        val roomRepos = repositories.map {
            RoomGithubRepository(
                it.id.toString(),
                it.name,
                it.description,
                it.forks,
                it.createdAt,
                it.language,
                roomUser.id
            )
        }
        database.repositoryDao.insert(roomRepos)
    }.subscribeOn(Schedulers.io())
}