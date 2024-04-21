package com.nmp.uts_anmp.model


data class Hobby(

    val id:String?,
    val name:String?,
    val title:String?,
    val description:String?,
    val news1:String?,
    val photoUrl:String,

)

data class User(

    val id:String?,
    val fname:String?,
    val lname:String?,
    val username:String?,
    val password: String?,

    )