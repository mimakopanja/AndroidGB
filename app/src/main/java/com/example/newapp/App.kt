package com.example.newapp

import android.app.Application
import com.example.newapp.di.AppComponent
import com.example.newapp.di.DaggerAppComponent
import com.example.newapp.di.module.AppModule
import com.example.newapp.mvp.model.entity.room.db.Database
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        Database.create(this)
    }
}