package com.example.banksimulatorandroid.View.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.banksimulatorandroid.Constants.EXTRA_USER_REST
import com.example.banksimulatorandroid.Constants.UPDATE_USER_INPUT_ERROR
import com.example.banksimulatorandroid.Model.Response.UserRest
import com.example.banksimulatorandroid.R
import com.example.banksimulatorandroid.ViewModel.DepositMoneyViewModel
import com.example.banksimulatorandroid.ViewModel.UpdateUserViewModel
import java.lang.NumberFormatException

class UpdateUserActivity : AppCompatActivity() {

    lateinit var userAccountDetails: UserRest
    lateinit var viewModel: UpdateUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user)

        viewModel = ViewModelProviders.of(this).get(UpdateUserViewModel(application)::class.java)
        userAccountDetails = intent.getParcelableExtra(EXTRA_USER_REST)

        val firstName = findViewById<TextView>(R.id.firstNameTxt)
        val lastName = findViewById<TextView>(R.id.lastNameTxt)
        val email = findViewById<TextView>(R.id.emailTxt)
        val password = findViewById<TextView>(R.id.passwordTxt)
        val updateUserBtn = findViewById<Button>(R.id.updateUserBtn)

        val toolbar = findViewById<Toolbar>(R.id.actionToolbar)
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeButtonEnabled(true)
            actionBar.setDisplayShowTitleEnabled(false)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_w)
        }

        observeViewModel()

        updateUserBtn.setOnClickListener {
            try {
                viewModel.update(firstName.text.toString(), lastName.text.toString(),
                    email.text.toString(), password.text.toString(), userAccountDetails.userId)
            } catch (e: NumberFormatException) {
                Toast.makeText(this, UPDATE_USER_INPUT_ERROR, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.userRest.observe(this, Observer { user ->
            user?.let {
                finish()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return return when(item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
