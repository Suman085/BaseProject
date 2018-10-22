package com.mic.debrismanagement.mvp.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Suman on 2/15/2018.
 */
@Entity(tableName = "user")
class User {

    @PrimaryKey(autoGenerate = false)
    var userId: Int? = null
    @ColumnInfo(name = "email")
    var email: String? = null
    @ColumnInfo(name = "name")
    var name: String? = null
    @ColumnInfo(name = "username")
    var userName: String? = null
    @ColumnInfo(name = "password")
    var password: String? = null
    @ColumnInfo(name = "location")
    var location: String? = null
    @ColumnInfo(name = "workLocation")
    var workLocation: String? = null
    @ColumnInfo(name = "Status")
    var Status: String? = null
    @ColumnInfo(name = "mobile")
    var mobile: String? = null
}
