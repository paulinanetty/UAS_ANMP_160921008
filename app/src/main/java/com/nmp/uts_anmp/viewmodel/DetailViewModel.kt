package com.nmp.uts_anmp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nmp.uts_anmp.model.Hobby

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val hobbyLD = MutableLiveData<Hobby>()
    private val TAG = "volleyTag"
    private var queue: RequestQueue?=null
    fun fetch(id:String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.0.105/anmpexercise/data.php?id=${id}"


        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val result = Gson().fromJson(it, Hobby::class.java)
                hobbyLD.value = result

                Log.d("showvoley", result.toString())


                Log.d("showvoley", it)
            },
            {
                Log.d("showvoley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}