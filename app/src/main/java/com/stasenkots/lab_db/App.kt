/*
 * This file is a part of the Yandex Advertising Network
 *
 * Version for Android (C) 2021 YANDEX
 *
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at https://legal.yandex.com/partner_ch/
 */

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