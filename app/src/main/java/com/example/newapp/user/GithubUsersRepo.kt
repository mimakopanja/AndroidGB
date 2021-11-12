package com.example.newapp.user

class GithubUsersRepo {
    private val repositories =
        (0..10).map { GithubUser("user $it") }

    fun getUsers(): List<GithubUser>{
        return repositories
    }
}