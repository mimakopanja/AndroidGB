package com.example.newapp.di

import com.example.newapp.MainActivity
import com.example.newapp.di.module.*
import com.example.newapp.mvp.presenter.MainPresenter
import com.example.newapp.mvp.presenter.RepositoryPresenter
import com.example.newapp.mvp.presenter.UserPresenter
import com.example.newapp.mvp.presenter.UsersPresenter
import com.example.newapp.ui.adapter.UsersRecyclerViewAdapter
import com.example.newapp.ui.fragments.RepositoryFragment
import com.example.newapp.ui.fragments.UserFragment
import com.example.newapp.ui.fragments.UsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
    @Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepoModule::class,
        ImageLoaderModule::class
    ])

interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)

    fun inject(usersRVAdapter: UsersRecyclerViewAdapter)
    fun inject(userPresenter: UserPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
}