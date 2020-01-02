package com.example.banksimulatorandroid.View.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.banksimulatorandroid.Constants.EXTRA_USER_REST
import com.example.banksimulatorandroid.Model.Response.UserRest
import com.example.banksimulatorandroid.R
import com.example.banksimulatorandroid.ViewModel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel(application)::class.java)

        val email = findViewById<TextView>(R.id.emailTxt)
        val password = findViewById<TextView>(R.id.passwordTxt)
        val loginBtn = findViewById<Button>(R.id.loginBtn)

        observeViewModel()

        loginBtn.setOnClickListener {
            viewModel.loginUser(email.text.toString(), password.text.toString())
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
