package com.example.newapp.screen

import com.example.newapp.user.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun userItem(user: GithubUser): Screen
}