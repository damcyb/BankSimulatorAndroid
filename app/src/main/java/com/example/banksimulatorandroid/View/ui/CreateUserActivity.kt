package com.example.banksimulatorandroid.View.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.banksimulatorandroid.R
import com.example.banksimulatorandroid.ViewModel.CreateUserViewModel
import com.example.banksimulatorandroid.ViewModel.LoginViewModel

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

        createUserBtn.setOnClickListener {
            viewModel.createUser(firstName.text.toString(), lastName.text.toString(),
                email.text.toString(), password.text.toString())
            //move to another activity
        }
    }
}
