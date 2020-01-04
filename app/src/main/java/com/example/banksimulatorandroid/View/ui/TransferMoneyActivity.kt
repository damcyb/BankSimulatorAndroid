package com.example.banksimulatorandroid.View.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.banksimulatorandroid.Constants.EXTRA_USER_REST
import com.example.banksimulatorandroid.Constants.TRANSFER_INPUT_ERROR
import com.example.banksimulatorandroid.Model.Response.UserRest
import com.example.banksimulatorandroid.R
import com.example.banksimulatorandroid.ViewModel.TransferMoneyViewModel
import com.example.banksimulatorandroid.ViewModel.WithdrawMoneyViewModel
import java.lang.NumberFormatException

class TransferMoneyActivity : AppCompatActivity() {

    lateinit var userAccountDetails: UserRest
    lateinit var viewModel: TransferMoneyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_money)

        viewModel = ViewModelProviders.of(this).get(TransferMoneyViewModel(application)::class.java)

        userAccountDetails = intent.getParcelableExtra(EXTRA_USER_REST)

        val toolbar = findViewById<Toolbar>(R.id.actionToolbar)
        setSupportActionBar(toolbar)

        val receiverFirstName = findViewById<EditText>(R.id.receiverFirstNameTxt)
        val receiverLastName = findViewById<EditText>(R.id.receiverLastNameTxt)
        val receiverAccountNumber = findViewById<EditText>(R.id.receiverAccountNumberTxt2)
        val transferredMoney = findViewById<EditText>(R.id.transferredMoneyTxt)
        val transferMoneyBtn = findViewById<Button>(R.id.transferMoneyBtn)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeButtonEnabled(true)
            actionBar.setDisplayShowTitleEnabled(false)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_w)
        }

        observeViewModel()

        transferMoneyBtn.setOnClickListener {
            try {
                viewModel.transfer(receiverFirstName.text.toString(), receiverLastName.text.toString(),
                    receiverAccountNumber.text.toString(), transferredMoney.text.toString().toDouble(),
                    userAccountDetails.userId)
            } catch (e: NumberFormatException) {
                Toast.makeText(this, TRANSFER_INPUT_ERROR, Toast.LENGTH_SHORT).show()
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
