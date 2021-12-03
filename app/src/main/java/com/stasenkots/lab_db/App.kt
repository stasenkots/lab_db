package com.stasenkots.lab_db

import android.app.Application
import androidx.room.Room
import com.stasenkots.lab_db.db.UserDatabase

class App : Application() {

    val userDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java, "database-user"
        ).build()
    }
}