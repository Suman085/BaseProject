package com.huluhive.travelsandtourism.database

import android.arch.persistence.room.*
import com.mic.rims.mvp.model.User
import io.reactivex.Flowable

/**
 * Created by User on 8/22/2017.
 */
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM user WHERE id = :arg0")
    fun getUser(id:Long): Flowable<User>

    @Query("DELETE FROM user WHERE id=:arg0")
    fun remove(id:Long)

    @Update
    fun updateUser(user: User)

}