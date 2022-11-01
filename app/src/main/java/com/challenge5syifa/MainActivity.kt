package com.challenge5syifa

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.challenge5syifa.databinding.ActivityMainBinding
import com.challenge5syifa.room.AppDatabase
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        db = AppDatabase.db(this)
        sharedPref = getSharedPreferences("logged", Context.MODE_PRIVATE)
        //val navHostFragment = bind.navHostFragment as NavHostFragment
        //val navController = navHostFragment.navController
    }
    companion object{
        lateinit var db: AppDatabase
        lateinit var sharedPref: SharedPreferences
        val gson: Gson = Gson()
    }
}