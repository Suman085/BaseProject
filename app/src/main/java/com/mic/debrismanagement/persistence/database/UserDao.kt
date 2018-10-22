package com.mic.debrismanagement.persistence.database

import android.arch.persistence.room.*
import com.mic.debrismanagement.mvp.model.User
import io.reactivex.Flowable

/**
 * Created by User on 8/22/2017.
 */
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM user WHERE userId = :id")
    fun getUser(id:Long): Flowable<User>

    @Query("DELETE FROM user WHERE userId=:id")
    fun remove(id:Long)

    @Update
    fun updateUser(user: User)

}