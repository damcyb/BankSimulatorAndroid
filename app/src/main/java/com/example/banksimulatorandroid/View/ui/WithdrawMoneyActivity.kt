package com.example.banksimulatorandroid.View.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.banksimulatorandroid.Constants.EXTRA_USER_REST
import com.example.banksimulatorandroid.Model.Response.UserRest
import com.example.banksimulatorandroid.R
import com.example.banksimulatorandroid.ViewModel.DepositMoneyViewModel
import com.example.banksimulatorandroid.ViewModel.WithdrawMoneyViewModel
import kotlinx.android.synthetic.main.activity_user_account.*

class WithdrawMoneyActivity : AppCompatActivity() {

    lateinit var userAccountDetails: UserRest
    lateinit var viewModel: WithdrawMoneyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withdraw_money)

        viewModel = ViewModelProviders.of(this).get(WithdrawMoneyViewModel(application)::class.java)

        userAccountDetails = intent.getParcelableExtra(EXTRA_USER_REST)

        val withdrawMoneyBtn = findViewById<Button>(R.id.withdrawBtn)
        val withdrawMoneyTxt = findViewById<EditText>(R.id.withdrawTxt)
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

        withdrawMoneyBtn.setOnClickListener {
            viewModel.withdraw(withdrawMoneyTxt.text.toString().toDouble(),userAccountDetails.userId)
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
