/*
 * This file is a part of the Yandex Advertising Network
 *
 * Version for Android (C) 2021 YANDEX
 *
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at https://legal.yandex.com/partner_ch/
 */


package com.stasenkots.lab_db

import androidx.appcompat.app.AppCompatActivity

val AppCompatActivity.userDao
    get() = app.userDatabase.userDao()

val AppCompatActivity.app
    get() = (application as App)