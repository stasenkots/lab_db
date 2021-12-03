
package com.stasenkots.lab_db.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.stasenkots.lab_db.db.User
import com.stasenkots.lab_db.db.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivityViewModel(private val userDao: UserDao) : ViewModel() {

    val allUsersCount = userDao.getAllUsersCount().asLiveData()
    val filter = MutableLiveData("")
    val filteredUsers = MutableLiveData<List<User>>()

    init {
        filter.observeForever { filter ->
            viewModelScope.launch(Dispatchers.IO) {
                userDao.getUsers(filter).collect {
                    filteredUsers.postValue(it)
                }
            }
        }
    }

}