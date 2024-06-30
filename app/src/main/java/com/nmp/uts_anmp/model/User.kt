package com.nmp.uts_anmp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @ColumnInfo(name = "fname")
    var fname: String?,
    @ColumnInfo(name = "lname")
    var lname: String?,
    @ColumnInfo(name = "username")
    var username: String?,
    @ColumnInfo(name = "password")
    var password: String?,
    @ColumnInfo(name = "email")
    var email: String?,

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}