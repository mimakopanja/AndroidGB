package com.example.newapp.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
class GithubRepository (
    @Expose val id: String? = null,
    @Expose val name: String? = null,
    @Expose val description: String? = null,
    @Expose val forks: Int? = null,
    @Expose val createdAt: String? = null,
    @Expose val language: String? = null,
    @Expose val userId: String? = null
): Parcelable