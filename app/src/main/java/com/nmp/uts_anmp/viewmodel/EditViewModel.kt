package com.nmp.uts_anmp.viewmodel

import com.nmp.uts_anmp.model.Hobby
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.nmp.uts_anmp.model.User
import com.nmp.uts_anmp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class EditViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
//    val userLoadErrorLD = MutableLiveData<Boolean>()
    private val job = Job()
    val hobbyLD = MutableLiveData<Hobby>()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
//    fun update(title: String?, name: String?, description: String?, photourl: String?, id: Int) {
//        launch {
//            val db = buildDb(getApplication())
//            db.hobbyDao().update(title.toString(),name.toString(),description.toString(),photourl.toString(),id)
//        }
//    }
    fun update(hobby: Hobby) {
        launch {
            val db = buildDb(getApplication())
            db.hobbyDao().update(hobby)
        }
    }

    fun addTodo(list: List<Hobby>) {
        launch {
            val db = buildDb(getApplication())
            db.hobbyDao().insertUser(*list.toTypedArray())
        }
    }
    fun fetch(id:Int) {
        launch {
            val db = buildDb(getApplication())
            hobbyLD.postValue(db.hobbyDao().selectHobby(id))
        }
    }

//    private val TAG = "volleyTag"
//    private var queue: RequestQueue? = null

//    fun updateUser(fname: String, lname: String, password: String,username: String) {
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "http://192.168.122.194/anmpexercise/update.php"
//
//        val user = HashMap<String, String>()
//        user["username"] = username
//        user["new_fname"] = fname
//        user["new_lname"] = lname
//        user["new_password"] = password
//
//
//        val stringRequest = object : StringRequest(
//            Method.POST, url,
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
//
//    override fun onCleared() {
//        super.onCleared()
//        queue?.cancelAll(TAG)
//    }
}