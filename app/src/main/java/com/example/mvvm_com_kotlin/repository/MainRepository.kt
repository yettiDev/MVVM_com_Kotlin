package com.example.mvvm_com_kotlin.repository

import com.example.mvvm_com_kotlin.model.Live
import com.example.mvvm_com_kotlin.rest.RetrofitService
import retrofit2.Call

class MainRepository(private val retrofitService: RetrofitService) {

    fun getAllLives(): Call<List<Live>> {
        return retrofitService.getAllLives()
    }
}