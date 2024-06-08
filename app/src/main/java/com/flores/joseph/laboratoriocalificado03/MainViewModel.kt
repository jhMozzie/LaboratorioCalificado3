package com.flores.joseph.laboratoriocalificado03

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainViewModel : ViewModel(){
    val errorApi = MutableLiveData<String>()
    val listProfesor = MutableLiveData<List<ProfesorResponse>>()

    init{
        getAllProfesores()
    }

    private fun getAllProfesores() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = getRetrofit().create(ProfesorApi::class.java).getProfesores()
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d("API Data", "Data received: ${it.teachers}")
                        listProfesor.postValue(it.teachers)
                    }
                } else {
                    Log.d("API Error", "Failed with code ${response.code()} and message ${response.message()}")
                }
            } catch (e: Exception) {
                errorApi.postValue("Exception: ${e.message}")
                Log.e("API Error", "Exception in API call", e)
            }
        }
    }



    private fun getRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("NAME-HEADER", "VALUE-HEADER")
                .build()
            chain.proceed(newRequest)
        }

        val client = httpClient.build()

        return Retrofit.Builder()
            .baseUrl("https://private-effe28-tecsup1.apiary-mock.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}