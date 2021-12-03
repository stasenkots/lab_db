package com.stasenkots.lab_db.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.stasenkots.lab_db.R
import com.stasenkots.lab_db.add_user.AddUserActivity
import com.stasenkots.lab_db.databinding.ActivityMainBinding
import com.stasenkots.lab_db.userDao

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val userAdapter = UserAdapter()

    private val viewModel by viewModels<MainActivityViewModel> {
        MainActivityViewModelFactory(userDao)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
            val decoration =
                DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)

        }

        viewModel.allUsersCount.observe(this) { usersCount ->
            binding.usersTotalCount.text = getString(R.string.total_users_count, usersCount)
        }

        viewModel.filteredUsers.observe(this) { users ->
            binding.usersFilteredCount.text = getString(R.string.filtered_users_count, users.size)
            userAdapter.submitList(users)
        }

        binding.filter.doOnTextChanged { text, _, _, _ ->
            viewModel.filter.postValue(text.toString())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_user -> {
                val intent = Intent(this, AddUserActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}