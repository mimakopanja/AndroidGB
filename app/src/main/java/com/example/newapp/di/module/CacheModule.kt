package com.example.newapp.di.module


import androidx.room.Room
import com.example.newapp.App
import com.example.newapp.DATABASE_NAME
import com.example.newapp.mvp.model.cache.IGithubRepositoriesCache
import com.example.newapp.mvp.model.cache.IGithubUsersCache
import com.example.newapp.mvp.model.cache.room.RoomGithubRepositoriesCache
import com.example.newapp.mvp.model.cache.room.RoomGithubUsersCache
import dagger.Module
import com.example.newapp.mvp.model.entity.room.db.Database
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(
        app,
        Database::class.java,
    DATABASE_NAME).build()

    @Singleton
    @Provides
    fun usersCache(database: Database): IGithubUsersCache = RoomGithubUsersCache(database)

    @Singleton
    @Provides
    fun repositoriesCache(database: Database): IGithubRepositoriesCache = RoomGithubRepositoriesCache(database)
}