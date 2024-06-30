package com.nmp.uts_anmp.viewmodel

import com.nmp.uts_anmp.model.Hobby
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
import com.nmp.uts_anmp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeViewModel(application: Application): AndroidViewModel(application), CoroutineScope {

    val hobbyLD = MutableLiveData<List<Hobby>>()
    val hobbyLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
//    private val TAG = "volleyTag"
//    private var queue: RequestQueue?=null

    fun refresh() {
        loadingLD.value = true
        hobbyLoadErrorLD.value = false
        launch {
            val db = buildDb(getApplication())

            hobbyLD.postValue(db.hobbyDao().getAllHobbies())
            loadingLD.postValue(false)
        }
    }
//    fun refresh() {
//        loadingLD.value = true
//        hobbyLoadErrorLD.value = false
//
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "http://192.168.122.194/anmpexercise/data.php"
//
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//
//                val sType = object : TypeToken<List<Hobby>>() { }.type
//                val result = Gson().fromJson<List<Hobby>>(it, sType)
//                hobbyLD.value = result as ArrayList<Hobby>?
//                loadingLD.value = false
//
//                Log.d("showvoley", result.toString())
//
//
//                loadingLD.value = false
//                Log.d("showvoley", it)
//            },
//            {
//                Log.d("showvoley", it.toString())
//                hobbyLoadErrorLD.value = false
//                loadingLD.value = false
//            })
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
//
//    }


//    override fun onCleared() {
//        super.onCleared()
//        queue?.cancelAll(TAG)
//    }

//    fun clearTask(hobby: Hobby) {
//        launch {
//            val db = buildDb(getApplication())
//            db.hobbyDao().deleteHobby(hobby)
//
//            hobbyLD.postValue(db.hobbyDao().getAllHobbies())
//        }
//    }
}