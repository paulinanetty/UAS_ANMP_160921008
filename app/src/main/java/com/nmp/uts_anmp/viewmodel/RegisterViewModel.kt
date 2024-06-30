package com.nmp.uts_anmp.viewmodel

import com.nmp.uts_anmp.model.User
import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.nmp.uts_anmp.model.Hobby
import com.nmp.uts_anmp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

class RegisterViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val userLoadErrorLD = MutableLiveData<Boolean>()
//    private val TAG = "volleyTag"
//    private var queue: RequestQueue? = null
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
//    fun refresh() {
//        userLoadErrorLD.value = false
//        launch {
//            val db = buildDb(getApplication())
//
////            todoLD.postValue(db.todoDao().selectAllTodo())
////            loadingLD.postValue(false)
//        }
//    }
    fun addUser(list: List<User>) {
        launch {
            val db = buildDb(getApplication())
            db.userDao().insertUser(*list.toTypedArray())
        }
    }

//    fun addUser(username: String, fname: String, lname: String, email: String, password: String) {
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "http://192.168.122.194/anmpexercise/register.php"
//
//        val user = HashMap<String, String>()
//        user["fname"] = fname
//        user["lname"] = lname
//        user["email"] = email
//        user["password"] = password
//        user["username"] = username
//
//        val stringRequest = object : StringRequest(Method.POST, url,
//            Response.Listener { response ->
//                val jsonObject = JSONObject(response)
//                val result = jsonObject.optString("message", "")
//                Log.d("showvoley", response.toString())
//                if (result == "success") {
//                    userLoadErrorLD.value = true
//
//                } else {
//                    userLoadErrorLD.value = false
//                    Toast.makeText(getApplication(), result, Toast.LENGTH_SHORT).show()
//                }
//
//            },
//            Response.ErrorListener { error ->
//                Log.d("showvoley", error.toString())
//                userLoadErrorLD.value = false
//            }) {
//
//            override fun getParams(): Map<String, String> {
//                return user
//            }
//        }
//
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
//    }

//    override fun onCleared() {
//        super.onCleared()
//        queue?.cancelAll(TAG)
//    }
//fun clearTask(user: User) {
//    launch {
//        val db = buildDb(getApplication())
//        db.userDao().deleteUser(user)
//
//        //todoLD.postValue(db.todoDao().selectAllTodo())
//    }
}
