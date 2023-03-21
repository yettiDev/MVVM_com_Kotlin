package com.example.mvvm_com_kotlin.viewModelMain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_com_kotlin.repository.MainRepository

class ViewModelFactoryMain(private val repository: MainRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ViewModelMain::class.java)) {
            ViewModelMain(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}