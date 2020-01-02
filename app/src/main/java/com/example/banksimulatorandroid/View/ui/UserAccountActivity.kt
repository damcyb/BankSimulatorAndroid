package com.example.banksimulatorandroid.View.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banksimulatorandroid.Constants.EXTRA_USER_REST
import com.example.banksimulatorandroid.Model.Response.UserRest
import com.example.banksimulatorandroid.R
import com.example.banksimulatorandroid.View.Adapter.TransferListAdapter
import com.example.banksimulatorandroid.ViewModel.UserAccountViewModel
import kotlinx.android.synthetic.main.activity_user_account.*
import java.lang.StringBuilder

class UserAccountActivity : AppCompatActivity() {

    lateinit var userAccountDetails: UserRest
    lateinit var viewModel: UserAccountViewModel
    private val transfersAdapter = TransferListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_account)
        userAccountDetails = intent.getParcelableExtra(EXTRA_USER_REST)

        val balanceTxt = findViewById<TextView>(R.id.balanceTxt)
        val accountNumberTxt = findViewById<TextView>(R.id.accountNumberTxt)

        val balanceString = "${String.format("%.2f", userAccountDetails.balance)} PLN"
        val accountNumberStringBuilder = StringBuilder()
        accountNumberStringBuilder.append(userAccountDetails.accountNumber)
        accountNumberStringBuilder.insert(2, " ").insert(7, " ")
            .insert(12, " ").insert(17, " ").insert(22, " ").insert(27, " ")

        balanceTxt.text = balanceString
        accountNumberTxt.text = accountNumberStringBuilder

        viewModel = ViewModelProviders.of(this).get(UserAccountViewModel(application)::class.java)
        viewModel.refresh(userAccountDetails.userId)

        transferList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = transfersAdapter
            }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.transferList.observe(this, Observer { transfers ->
            transfers?.let {
                transferList.visibility = View.VISIBLE
                transfersAdapter.updateTransfers(it) }
        })

        viewModel.transferListError.observe(this, Observer { isError ->
            isError?.let { loading_error.visibility = if(it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                transfer_progress_bar.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    loading_error.visibility = View.GONE
                    transferList.visibility = View.GONE
                }
            }
        })
    }
}
