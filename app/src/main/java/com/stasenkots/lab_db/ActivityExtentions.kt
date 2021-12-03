package com.stasenkots.lab_db

import androidx.appcompat.app.AppCompatActivity

val AppCompatActivity.userDao
    get() = app.userDatabase.userDao()

val AppCompatActivity.app
    get() = (application as App)