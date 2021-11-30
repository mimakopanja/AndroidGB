package com.example.newapp.mvp.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomCachedImage (
    @PrimaryKey val url: String,
            val localPath: String
    )