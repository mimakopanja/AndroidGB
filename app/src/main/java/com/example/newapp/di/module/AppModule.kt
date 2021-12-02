package com.example.newapp.di.module

import com.example.newapp.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }
}