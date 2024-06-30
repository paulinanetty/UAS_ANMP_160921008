package com.nmp.uts_anmp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import androidx.room.OnConflictStrategy

@Dao
interface HobbyDao {
    @Query("SELECT * FROM hobbies")
    fun getAllHobbies(): List<Hobby>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg hobby: Hobby)

    @Query("SELECT * FROM hobbies WHERE id=:id")
    fun selectHobby(id:Int): Hobby

//    @Query("UPDATE hobbies SET title=:title, name=:name, description=:description, photoUrl=:photo WHERE id = :id")
//    fun update(title:String, name:String, description:String, photo: String, id:Int)
    @Update
    fun update(hobby: Hobby)

    @Delete
    fun deleteHobby(hobby: Hobby)
}

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    fun getUser(username: String, password: String): User?
//    @Query("UPDATE users SET isLoggedIn = 1 WHERE username = :username")
//    fun loginUser(username: String)
//
//    @Query("UPDATE users SET isLoggedIn = 0 WHERE username = :username")
//    fun logoutUser(username: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg user: User)

    @Query("SELECT * FROM users WHERE id=:id")
    fun selectUser(id: String?): User

//    @Query("UPDATE users SET fname=:fname, lname=:lname, username=:username, password=:password WHERE id = :id")
//    fun update(fname:String, lname:String, username:String, password: String, id:Int)
    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)
}
