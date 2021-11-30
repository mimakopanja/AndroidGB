package com.example.newapp.mvp.model.repo

import com.example.newapp.mvp.model.api.IDataSource
import com.example.newapp.mvp.model.cache.IGithubRepositoriesCache
import com.example.newapp.mvp.model.entity.GithubRepository
import com.example.newapp.mvp.model.entity.GithubUser
import com.example.newapp.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoriesRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    val cache: IGithubRepositoriesCache
): IGithubRepositoriesRepo {

    override fun getUserRepositories(user: GithubUser) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            user.reposUrl.let { url ->
                api.getUserRepos(url)
                    .flatMap { repositories ->
                        cache.putUserRepos(user, repositories).toSingleDefault(repositories)
                    }
            } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repositories URL!"))
                .subscribeOn(Schedulers.io())
        } else {
            cache.getUserRepos(user)
        }
    }.subscribeOn(Schedulers.io())
}