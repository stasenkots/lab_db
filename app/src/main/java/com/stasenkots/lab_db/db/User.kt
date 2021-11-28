/*
 * This file is a part of the Yandex Advertising Network
 *
 * Version for Android (C) 2021 YANDEX
 *
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at https://legal.yandex.com/partner_ch/
 */

package com.stasenkots.lab_db.db

import androidx.room.Entity

@Entity(tableName = "user_table", primaryKeys = ["name", "surname"])
data class User(
    val name: String,
    val surname: String,
    val comment: String
)