package com.example.newapp.mvp.model.repo

import com.example.newapp.mvp.model.entity.GithubRepository
import com.example.newapp.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesRepo {
    fun getUserRepositories(user: GithubUser): Single<List<GithubRepository>>
}