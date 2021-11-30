package com.example.newapp.mvp.model.cache

import com.example.newapp.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface IImageCache {
    fun saveImage(url: String, bytes: ByteArray): Completable
    fun getCachedImage(url: String): Maybe<ByteArray?>
}