package com.example.mvvm_com_kotlin


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.mvvm_com_kotlin.adapters.LiveAdapater
import com.example.mvvm_com_kotlin.databinding.ActivityMainBinding
import com.example.mvvm_com_kotlin.repository.MainRepository
import com.example.mvvm_com_kotlin.rest.RetrofitService
import com.example.mvvm_com_kotlin.viewModelMain.ViewModelFactoryMain
import com.example.mvvm_com_kotlin.viewModelMain.ViewModelMain

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: ViewModelMain
    private val retrofitService= RetrofitService.getInstace()
    private val adapter= LiveAdapater{
        openLink(it.link)
    }

    private fun openLink(link:String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(intent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mainViewModel= ViewModelProvider(this, ViewModelFactoryMain(MainRepository(retrofitService)))
            .get(ViewModelMain::class.java)
        binding.recyclerview.adapter=adapter


    }

    override fun onStart() {
        super.onStart()
        mainViewModel.liveList.observe(this, Observer { lives->
            adapter.setLiveList(lives)
        })
    }

    override fun onPostResume() {
        super.onPostResume()
        mainViewModel.getAllLives()
    }


}