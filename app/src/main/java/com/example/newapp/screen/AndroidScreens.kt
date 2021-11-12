package com.example.newapp.screen

import com.example.newapp.user.GithubUser
import com.example.newapp.user.UserItemFragment
import com.example.newapp.user.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens: IScreens {
    override fun users(): Screen {
        return FragmentScreen { UsersFragment.newInstance() }
    }
    override fun userItem(user: GithubUser): Screen {
        return FragmentScreen { UserItemFragment.newInstance(user)}
    }
}