/*
 * This file is a part of the Yandex Advertising Network
 *
 * Version for Android (C) 2021 YANDEX
 *
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at https://legal.yandex.com/partner_ch/
 */

package com.stasenkots.lab_db.add_user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stasenkots.lab_db.db.User
import com.stasenkots.lab_db.db.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddUserViewModel(private val userDao: UserDao) : ViewModel() {

    val isUserSaved = MutableLiveData<Boolean>()

    fun saveUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insertUser(user)
            isUserSaved.postValue(true)
        }
    }

}