package com.example.newapp.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newapp.DATABASE_NAME
import com.example.newapp.mvp.model.entity.RoomCachedImage
import com.example.newapp.mvp.model.entity.room.RoomGithubRepository
import com.example.newapp.mvp.model.entity.room.RoomGithubUser
import com.example.newapp.mvp.model.entity.room.dao.ImageDao
import com.example.newapp.mvp.model.entity.room.dao.RepositoryDao
import com.example.newapp.mvp.model.entity.room.dao.UserDao
import java.lang.RuntimeException

@androidx.room.Database(
    entities = [
        RoomGithubUser::class,
        RoomGithubRepository::class,
        RoomCachedImage::class
    ], version = 1
)

abstract class Database: RoomDatabase() {

    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
    abstract val imageDao: ImageDao

    companion object{
        private var instance: Database? = null
        fun getInstance() = instance ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context) {
            if (instance == null){
                instance = Room.databaseBuilder(
                    context,
                    Database::class.java,
                    DATABASE_NAME
                ).build()
            }
        }
    }
}