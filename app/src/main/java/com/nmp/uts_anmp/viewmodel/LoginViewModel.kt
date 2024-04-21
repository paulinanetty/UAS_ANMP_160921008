package com.nmp.uts_anmp.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.nmp.uts_anmp.model.Hobby
import com.nmp.uts_anmp.model.User
import org.json.JSONObject

class LoginViewModel(application: Application): AndroidViewModel(application) {
    val userLD = MutableLiveData<Boolean>()
    private val TAG = "volleyTag"
    private var queue: RequestQueue?=null
    fun login(username:String, password:String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.0.105/anmpexercise/login.php"

        val user = HashMap<String, String>()
        user["password"] = password
        user["username"] = username

        val stringRequest = object : StringRequest(Method.POST, url,
            Response.Listener { response ->
                val jsonObject = JSONObject(response)
                val result = jsonObject.optString("msg", "")
                Log.d("showvoley", response.toString())
                if (result == "Successfully Log In") {
                    userLD.value = true

                } else {
                    userLD.value = false
                    Toast.makeText(getApplication(), result, Toast.LENGTH_SHORT).show()
                }

            },
            Response.ErrorListener { error ->
                Log.d("showvoley", error.toString())
                userLD.value = false
            }) {

            override fun getParams(): Map<String, String> {
                return user
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}