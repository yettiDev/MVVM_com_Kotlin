package com.example.mvvm_com_kotlin.viewModelMain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_com_kotlin.model.Live
import com.example.mvvm_com_kotlin.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelMain(private val repository: MainRepository):ViewModel() {
    val liveList= MutableLiveData<List<Live>>()
    val errorMessage=MutableLiveData<String>()

    fun getAllLives(){
        val repository=repository.getAllLives()

        repository.enqueue(object:Callback<List<Live>>{
            override fun onResponse(call: Call<List<Live>>, response: Response<List<Live>>) {
                liveList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Live>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

}