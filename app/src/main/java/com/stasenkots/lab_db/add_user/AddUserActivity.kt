package com.stasenkots.lab_db.add_user

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.stasenkots.lab_db.R
import com.stasenkots.lab_db.databinding.ActivityAddUserBinding
import com.stasenkots.lab_db.db.User
import com.stasenkots.lab_db.userDao

class AddUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddUserBinding

    private val viewModel by viewModels<AddUserViewModel> {
        AddUserActivityViewModelFactory(userDao)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.name.doOnTextChanged { _, _, _, _ ->
            binding.nameLayout.error = null
        }
        binding.surname.doOnTextChanged { _, _, _, _ ->
            binding.surnameLayout.error = null
        }
        binding.comment.doOnTextChanged { _, _, _, _ ->
            binding.surnameLayout.error = null
        }

        binding.cancel.setOnClickListener {
            finish()
        }

        setupSave()
    }

    private fun setupSave() {
        binding.save.setOnClickListener {
            if (!validFields()) return@setOnClickListener
            binding.progressBar.isVisible = true
            val user = User(
                name = binding.name.text.toString(),
                surname = binding.surname.text.toString(),
                comment = binding.comment.text.toString()
            )
            viewModel.saveUser(user)
        }

        viewModel.isUserSaved.observe(this) {
            binding.progressBar.isVisible = false
            Toast.makeText(this, R.string.user_saved, Toast.LENGTH_SHORT).show()
            finish()
        }
    }


    private fun validFields(): Boolean {
        return when {
            binding.name.text.isNullOrBlank() -> {
                binding.nameLayout.error = getString(R.string.not_empty)
                false
            }
            binding.surname.text.isNullOrBlank() -> {
                binding.surnameLayout.error = getString(R.string.not_empty)
                false
            }
            else -> true
        }
    }
}