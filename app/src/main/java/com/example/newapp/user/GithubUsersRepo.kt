package com.example.newapp.user

import com.example.newapp.mvp.model.entity.GithubUser

class GithubUsersRepo {
    private val repositories =
        (0..10).map { GithubUser("user $it") }
    
}