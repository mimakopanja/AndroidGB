package com.example.newapp.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.reactivex.rxjava3.annotations.NonNull

@Entity
data class RoomGithubUser (
    @PrimaryKey @NonNull var id: String,
    var login: String? = null,
    var avatarUrl: String? = null,
    var reposUrl: String? = null
        )