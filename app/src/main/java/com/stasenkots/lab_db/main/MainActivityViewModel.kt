/*
 * This file is a part of the Yandex Advertising Network
 *
 * Version for Android (C) 2021 YANDEX
 *
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at https://legal.yandex.com/partner_ch/
 */

package com.stasenkots.lab_db.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stasenkots.lab_db.db.User
import com.stasenkots.lab_db.db.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(private val userDao: UserDao) : ViewModel() {

    val users = MutableLiveData<List<User>>()
    val filter = MutableLiveData<String>()
    val filteredUsers = MutableLiveData<List<User>>()

    fun setup() {
        viewModelScope.launch(Dispatchers.IO) {
            users.postValue(userDao.getAllUsers())
            filteredUsers.postValue(userDao.getAllUsers())
        }

        filter.observeForever { filter ->
            viewModelScope.launch(Dispatchers.IO) {
                val list = if (filter.isEmpty()) {
                    userDao.getAllUsers()
                } else {
                    userDao.getUsers(filter)
                }
                filteredUsers.postValue(list)
            }
        }
    }

}