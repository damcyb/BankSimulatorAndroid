package com.example.banksimulatorandroid.View.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banksimulatorandroid.Constants.EXTRA_USER_REST
import com.example.banksimulatorandroid.Model.Response.UserRest
import com.example.banksimulatorandroid.R
import com.example.banksimulatorandroid.View.Adapter.TransferListAdapter
import com.example.banksimulatorandroid.ViewModel.UserAccountViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_user_account.*
import org.w3c.dom.Text
import java.lang.StringBuilder

class UserAccountActivity : AppCompatActivity() {

    lateinit var userAccountDetails: UserRest
    lateinit var viewModel: UserAccountViewModel
    private val transfersAdapter = TransferListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_account)
        userAccountDetails = intent.getParcelableExtra(EXTRA_USER_REST)

        viewModel = ViewModelProviders.of(this).get(UserAccountViewModel(application)::class.java)
        viewModel.refresh(userAccountDetails.userId)
        viewModel.getUser(userAccountDetails.userId)

        transferList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transfersAdapter
        }

        val header = (findViewById<View>(R.id.nav_view) as NavigationView).getHeaderView(0)
        val accountNumberTxt = findViewById<TextView>(R.id.accountNumberTxt)
        val navDrawerNameTxt = header.findViewById<TextView>(R.id.navDrawerNameTxt)
        val navDrawerEmailTxt = header.findViewById<TextView>(R.id.navDrawerEmailTxt)

        val accountNumberStringBuilder = StringBuilder()
        accountNumberStringBuilder.append(userAccountDetails.accountNumber)
        accountNumberStringBuilder.insert(2, " ").insert(7, " ")
            .insert(12, " ").insert(17, " ").insert(22, " ").insert(27, " ")
        val nameString = "${userAccountDetails.firstName} ${userAccountDetails.lastName}"
        val emailString = "${userAccountDetails.email}"

        accountNumberTxt.text = accountNumberStringBuilder
        navDrawerNameTxt.text = nameString
        navDrawerEmailTxt.text = emailString

        val toolbar = findViewById<Toolbar>(R.id.actionToolbar)
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeButtonEnabled(true)
            actionBar.setDisplayShowTitleEnabled(false)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        navDrawerOptionsSelected()

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.userRest.observe(this, Observer { user ->
            user?.let {
                balanceTxt.text = "${String.format("%.2f", viewModel.userRest.value!!.balance)} PLN"
                userAccountDetails = it
            }
        })

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return return when(item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navDrawerOptionsSelected() {
        val navDrawerView = findViewById<NavigationView>(R.id.nav_view)
        navDrawerView.setNavigationItemSelectedListener { menuItem ->

            if(menuItem.itemId == R.id.depositMoneyItm) {
                depositMoneyOptionSelected()
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            if(menuItem.itemId == R.id.withdrawMoneyItm) {
                withdrawMoneyOptionSelected()
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            if(menuItem.itemId == R.id.transferMoneyItm) {
                transferMoneyOptionSelected()
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            if(menuItem.itemId == R.id.updateUserDataItm) {
                updateUserDataOptionSelected()
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            if(menuItem.itemId == R.id.deleteAccountItm) {
                deleteAccountOptionSelected()
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            if(menuItem.itemId == R.id.logoutItm) {
                logoutOptionSelected()
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            true
        }
    }

    private fun depositMoneyOptionSelected() {
        val intent = Intent(this, DepositMoneyActivity::class.java)
        intent.putExtra(EXTRA_USER_REST, userAccountDetails)
        startActivity(intent)
    }
    private fun withdrawMoneyOptionSelected() {
        val intent = Intent(this, WithdrawMoneyActivity::class.java)
        intent.putExtra(EXTRA_USER_REST, userAccountDetails)
        startActivity(intent)
    }

    private fun transferMoneyOptionSelected() {
        val intent = Intent(this, TransferMoneyActivity::class.java)
        intent.putExtra(EXTRA_USER_REST, userAccountDetails)
        startActivity(intent)
    }

    private fun updateUserDataOptionSelected() {
        val intent = Intent(this, UpdateUserActivity::class.java)
        intent.putExtra(EXTRA_USER_REST, userAccountDetails)
        startActivity(intent)
    }

    private fun deleteAccountOptionSelected() {
        val intent = Intent(this, DeleteUserActivity::class.java)
        intent.putExtra(EXTRA_USER_REST, userAccountDetails)
        startActivity(intent)
    }

    private fun logoutOptionSelected() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.refresh(userAccountDetails.userId)
        viewModel.getUser(userAccountDetails.userId)

    }
}
