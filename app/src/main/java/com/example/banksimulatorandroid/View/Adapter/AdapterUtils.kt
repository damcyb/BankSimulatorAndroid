package com.example.banksimulatorandroid.View.Adapter

import android.graphics.Color
import java.lang.StringBuilder

class AdapterUtils {

    fun convertDate(date: String): String {
        return date.split(" ").get(0)
    }

    fun convertAccountNumber(accountNumber: String): StringBuilder {
        val accountNumberStringBuilder = StringBuilder()
        accountNumberStringBuilder.append(accountNumber)
        accountNumberStringBuilder.insert(2, " ").insert(7, " ")
            .insert(12, " ").insert(17, " ").insert(22, " ").insert(27, " ")
        return accountNumberStringBuilder
    }

    fun convertTransferredMoney(transferredMoney: Double): String {
        //return "${String.format("%.2f", transferredMoney.toString())} PLN"
        return "$transferredMoney PLN"
    }

    fun convertTransferDirection(transferDirection: String): Int {
        return if(transferDirection.equals("SENT") || transferDirection.equals("DEPOSIT"))
            Color.GREEN
        else
            Color.RED
    }
}