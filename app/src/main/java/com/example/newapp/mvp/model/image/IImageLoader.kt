package com.example.newapp.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}