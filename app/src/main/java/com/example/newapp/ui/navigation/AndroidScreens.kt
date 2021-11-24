package com.example.newapp.ui.navigation

import com.example.newapp.mvp.model.entity.GithubRepository
import com.example.newapp.mvp.model.entity.GithubUser
import com.example.newapp.mvp.model.navigation.IScreens
import com.example.newapp.ui.fragments.RepositoryFragment
import com.example.newapp.ui.fragments.UserFragment
import com.example.newapp.ui.fragments.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens: IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }

    override fun user(githubUser: GithubUser) =
        FragmentScreen { UserFragment.newInstance(githubUser) }

    override fun repository(repository: GithubRepository) = FragmentScreen {
        RepositoryFragment.newInstance(repository)
    }
}