package com.example.newapp.mvp.model.repo

import com.example.newapp.mvp.model.api.IDataSource
import com.example.newapp.mvp.model.entity.GithubRepository
import com.example.newapp.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(private val api: IDataSource): IGithubUsersRepo {
    override fun loadUsers(): Single<List<GithubUser>> = api.getUsers().subscribeOn(Schedulers.io())
    override fun loadUserRepos(url: String?): Single<List<GithubRepository>> = api.getUserRepos(url).subscribeOn(Schedulers.io())
}