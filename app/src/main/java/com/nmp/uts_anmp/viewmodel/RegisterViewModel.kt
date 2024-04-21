package com.nmp.uts_anmp.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    val userLoadErrorLD = MutableLiveData<Boolean>()
    private val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun addUser(username: String, fname: String, lname: String, email: String, password: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.0.105/anmpexercise/register.php"

        val user = HashMap<String, String>()
        user["fname"] = fname
        user["lname"] = lname
        user["email"] = email
        user["password"] = password
        user["username"] = username

        val stringRequest = object : StringRequest(Method.POST, url,
            Response.Listener { response ->
                val jsonObject = JSONObject(response)
                val result = jsonObject.optString("message", "")
                Log.d("showvoley", response.toString())
                if (result == "success") {
                    userLoadErrorLD.value = true

                } else {
                    userLoadErrorLD.value = false
                    Toast.makeText(getApplication(), result, Toast.LENGTH_SHORT).show()
                }

            },
            Response.ErrorListener { error ->
                Log.d("showvoley", error.toString())
                userLoadErrorLD.value = false
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