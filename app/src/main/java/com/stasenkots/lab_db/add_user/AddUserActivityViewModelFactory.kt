package com.stasenkots.lab_db.add_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stasenkots.lab_db.db.UserDao

class AddUserActivityViewModelFactory(private val userDao: UserDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddUserViewModel(userDao) as T
    }
}