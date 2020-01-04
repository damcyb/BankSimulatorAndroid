package com.example.banksimulatorandroid.View.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.banksimulatorandroid.Constants.DEPOSIT_INPUT_ERROR
import com.example.banksimulatorandroid.Constants.EXTRA_USER_REST
import com.example.banksimulatorandroid.Model.Response.UserRest
import com.example.banksimulatorandroid.R
import com.example.banksimulatorandroid.ViewModel.DepositMoneyViewModel
import com.example.banksimulatorandroid.ViewModel.LoginViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_user_account.*
import java.lang.NumberFormatException
import java.lang.StringBuilder

class DepositMoneyActivity : AppCompatActivity() {

    lateinit var userAccountDetails: UserRest
    lateinit var viewModel: DepositMoneyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deposit_money)

        viewModel = ViewModelProviders.of(this).get(DepositMoneyViewModel(application)::class.java)

        userAccountDetails = intent.getParcelableExtra(EXTRA_USER_REST)

        val depositMoneyBtn = findViewById<Button>(R.id.depositBtn)
        val depositMoneyTxt = findViewById<EditText>(R.id.depositTxt)
        val balanceString = "${String.format("%.2f", userAccountDetails.balance)} PLN"
        balanceTxt.text = balanceString

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

        depositMoneyBtn.setOnClickListener {
            try {
                viewModel.deposit(depositMoneyTxt.text.toString().toDouble(), userAccountDetails.userId)
            } catch (e: NumberFormatException) {
                Toast.makeText(this, DEPOSIT_INPUT_ERROR, Toast.LENGTH_LONG).show()
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
