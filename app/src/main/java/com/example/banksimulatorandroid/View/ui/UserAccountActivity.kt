package com.example.banksimulatorandroid.View.ui

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

        val header = (findViewById<View>(R.id.nav_view) as NavigationView).getHeaderView(0)
        val balanceTxt = findViewById<TextView>(R.id.balanceTxt)
        val accountNumberTxt = findViewById<TextView>(R.id.accountNumberTxt)
        val navDrawerNameTxt = header.findViewById<TextView>(R.id.navDrawerNameTxt)
        val navDrawerEmailTxt = header.findViewById<TextView>(R.id.navDrawerEmailTxt)

        val balanceString = "${String.format("%.2f", userAccountDetails.balance)} PLN"
        val accountNumberStringBuilder = StringBuilder()
        accountNumberStringBuilder.append(userAccountDetails.accountNumber)
        accountNumberStringBuilder.insert(2, " ").insert(7, " ")
            .insert(12, " ").insert(17, " ").insert(22, " ").insert(27, " ")
        val nameString = "${userAccountDetails.firstName} ${userAccountDetails.lastName}"
        val emailString = "${userAccountDetails.email}"

        balanceTxt.text = balanceString
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
            true
        }
    }

    private fun depositMoneyOptionSelected() {

    }
    private fun withdrawMoneyOptionSelected() {

    }

    private fun transferMoneyOptionSelected() {

    }

    private fun updateUserDataOptionSelected() {

    }

    private fun deleteAccountOptionSelected() {

    }
}
