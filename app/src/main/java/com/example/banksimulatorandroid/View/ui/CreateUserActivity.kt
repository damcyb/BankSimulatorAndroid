package com.example.banksimulatorandroid.View.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.banksimulatorandroid.Constants.CREATE_USER_INPUT_ERROR
import com.example.banksimulatorandroid.Constants.EXTRA_USER_REST
import com.example.banksimulatorandroid.Model.Response.UserRest
import com.example.banksimulatorandroid.R
import com.example.banksimulatorandroid.ViewModel.CreateUserViewModel
import com.example.banksimulatorandroid.ViewModel.LoginViewModel
import java.lang.NumberFormatException

class CreateUserActivity : AppCompatActivity() {

    lateinit var viewModel: CreateUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        viewModel = ViewModelProviders.of(this).get(CreateUserViewModel(application)::class.java)

        val firstName = findViewById<TextView>(R.id.firstNameTxt)
        val lastName = findViewById<TextView>(R.id.lastNameTxt)
        val email = findViewById<TextView>(R.id.emailTxt)
        val password = findViewById<TextView>(R.id.passwordTxt)
        val createUserBtn = findViewById<Button>(R.id.createUserBtn)

        observeViewModel()

        createUserBtn.setOnClickListener {
            try {
                viewModel.createUser(firstName.text.toString(), lastName.text.toString(),
                    email.text.toString(), password.text.toString())
            } catch (e: NumberFormatException) {
                Toast.makeText(this, CREATE_USER_INPUT_ERROR, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.userRest.observe(this, Observer { user ->
            user?.let {
                moveToUserAccountActivity(it)
            }
        })
    }

    private fun moveToUserAccountActivity(userAccountDetails: UserRest) {
        val intent = Intent(this, UserAccountActivity::class.java)
        intent.putExtra(EXTRA_USER_REST, userAccountDetails)
        startActivity(intent)
    }
}
