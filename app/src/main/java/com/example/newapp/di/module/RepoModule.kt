package com.example.newapp.di.module

import com.example.newapp.mvp.model.api.IDataSource
import com.example.newapp.mvp.model.cache.IGithubRepositoriesCache
import com.example.newapp.mvp.model.cache.IGithubUsersCache
import com.example.newapp.mvp.model.network.INetworkStatus
import com.example.newapp.mvp.model.repo.IGithubRepositoriesRepo
import com.example.newapp.mvp.model.repo.IGithubUsersRepo
import com.example.newapp.mvp.model.repo.RetrofitGithubRepositoriesRepo
import com.example.newapp.mvp.model.repo.RetrofitGithubUsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubUsersCache
    ): IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun repositoriesRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubRepositoriesCache
    ): IGithubRepositoriesRepo =
        RetrofitGithubRepositoriesRepo(api, networkStatus, cache)
}