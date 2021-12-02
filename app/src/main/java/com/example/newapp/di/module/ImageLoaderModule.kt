package com.example.newapp.di.module

import android.widget.ImageView
import com.example.newapp.App
import com.example.newapp.mvp.model.cache.IImageCache
import com.example.newapp.mvp.model.cache.room.ImageCache
import com.example.newapp.mvp.model.entity.RoomCachedImage
import com.example.newapp.mvp.model.entity.room.db.Database
import com.example.newapp.mvp.model.image.IImageLoader
import com.example.newapp.mvp.model.network.INetworkStatus
import com.example.newapp.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageLoaderModule {

    @Singleton
    @Provides
    fun imageLoader(
        imageCache: IImageCache,
        networkStatus: INetworkStatus
    ): IImageLoader<ImageView> = GlideImageLoader(imageCache, networkStatus)

    @Singleton
    @Provides
    fun imageCache(database: Database, app: App): IImageCache = ImageCache(database, app.cacheDir)
}