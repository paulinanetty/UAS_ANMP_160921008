package com.nmp.uts_anmp.viewmodel

import com.nmp.uts_anmp.model.User
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.nmp.uts_anmp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class LoginViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val userLD = MutableLiveData<User?>()
    private val job = Job()
//    private val TAG = "volleyTag"
//    private var queue: RequestQueue?=null
override val coroutineContext: CoroutineContext
    get() = job + Dispatchers.IO
//    fun fetch(id:Int) {
//        launch {
//            val db = buildDb(getApplication())
//            userLD.postValue(db.userDao().selectUser(id))
//        }
//    }
//    fun update(user:User) {
//        launch {
//            val db = buildDb(getApplication())
//            db.userDao().updateUser(user)
//        }
//    }
    fun login(username: String?, password: String?) {
        launch {
            val db = buildDb(getApplication())
            val user = db.userDao().getUser(username.toString(),password.toString())
//           // userLD.value = user

        //val user = userDao().getUser(username, password)
        withContext(Dispatchers.Main) {
            userLD.value = user
    }
        }
    }
    fun logout() {
        launch {
            withContext(Dispatchers.Main) {
                userLD.value = null
            }
        }
    }
    fun fetch(id: String?) {
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.userDao().selectUser(id))
        }
    }
    fun update(user: User) {
        launch {
            val db = buildDb(getApplication())
            db.userDao().updateUser(user)
            withContext(Dispatchers.Main) {
                userLD.value = user
            }
        }
    }
    fun delete(user: User) {
        launch {
            val db = buildDb(getApplication())
            db.userDao().deleteUser(user)
        }
    }
//    fun addUser(list: List<User>) {
//        launch {
//            val db = buildDb(getApplication())
//            db.userDao().insertUser(*list.toTypedArray())
//        }
//    }
//    fun login(username:String, password:String) {
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "http://192.168.122.194/anmpexercise/login.php"
//
//        val user = HashMap<String, String>()
//        user["password"] = password
//        user["username"] = username
//
//        val stringRequest = object : StringRequest(Method.POST, url,
//            Response.Listener { response ->
//                val jsonObject = JSONObject(response)
//                val result = jsonObject.optString("msg", "")
//                Log.d("showvoley", response.toString())
//                if (result == "Successfully Log In") {
//                    userLD.value = true
//
//                } else {
//                    userLD.value = false
//                    Toast.makeText(getApplication(), result, Toast.LENGTH_SHORT).show()
//                }
//
//            },
//            Response.ErrorListener { error ->
//                Log.d("showvoley", error.toString())
//                userLD.value = false
//            }) {
//
//            override fun getParams(): Map<String, String> {
//                return user
//            }
//        }
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
//    }
//    override fun onCleared() {
//        super.onCleared()
//        queue?.cancelAll(TAG)
//    }

}