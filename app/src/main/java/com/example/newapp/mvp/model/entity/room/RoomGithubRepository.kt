package com.example.newapp.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import io.reactivex.rxjava3.annotations.NonNull

@Entity(
    foreignKeys = [ ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomGithubRepository(
    @PrimaryKey @NonNull var id: String,
    var name: String?,
    var description: String?,
    var forks: Int?,
    var createdAt: String?,
    var language: String?,
    var userId: String?
)
