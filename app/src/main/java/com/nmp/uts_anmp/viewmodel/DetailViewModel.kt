package com.nmp.uts_anmp.viewmodel

import com.nmp.uts_anmp.model.Hobby
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.nmp.uts_anmp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val hobbyLD = MutableLiveData<Hobby>()
    private val job = Job()
//    private val TAG = "volleyTag"
//    private var queue: RequestQueue?=null
override val coroutineContext: CoroutineContext
    get() = job + Dispatchers.IO

    fun fetch(id:Int) {
        launch {
            val db = buildDb(getApplication())
            hobbyLD.postValue(db.hobbyDao().selectHobby(id))
        }
    }

//    fun fetch(id:String) {
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "http://192.168.122.194/anmpexercise/data.php?id=${id}"
//
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                val result = Gson().fromJson(it, Hobby::class.java)
//                hobbyLD.value = result
//
//                Log.d("showvoley", result.toString())
//
//
//                Log.d("showvoley", it)
//            },
//            {
//                Log.d("showvoley", it.toString())
//            })
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
//    }
//    override fun onCleared() {
//        super.onCleared()
//        queue?.cancelAll(TAG)
//    }

}