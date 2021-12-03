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