package com.example.newapp.mvp.model.entity.room.dao

import androidx.room.*
import com.example.newapp.mvp.model.entity.room.RoomGithubUser

@Dao
interface UserDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insert (user: RoomGithubUser)

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insert (vararg user: RoomGithubUser)

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insert (users: List<RoomGithubUser>)

    @Update
    fun update (user: RoomGithubUser)

    @Update
    fun update (vararg user: RoomGithubUser)

    @Update
    fun update (users: List<RoomGithubUser>)

    @Delete
    fun delete (user: RoomGithubUser)

    @Delete
    fun delete (vararg user: RoomGithubUser)

    @Delete
    fun delete (users: List<RoomGithubUser>)

    @Query("SELECT * FROM RoomGithubUser")
    fun getAll(): List<RoomGithubUser>

    @Query("SELECT * FROM RoomGithubUser WHERE login = :login LIMIT 1")
    fun findByLogin(login: String?): RoomGithubUser?

}