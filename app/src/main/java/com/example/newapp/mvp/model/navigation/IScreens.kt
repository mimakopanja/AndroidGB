package com.example.newapp.mvp.model.navigation



import com.example.newapp.mvp.model.entity.GithubRepository
import com.example.newapp.mvp.model.entity.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(githubUser: GithubUser): Screen
    fun repository(repository: GithubRepository): Screen
}