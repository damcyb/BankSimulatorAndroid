package com.example.banksimulatorandroid.View.Adapter

import android.graphics.Color
import android.graphics.ColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.banksimulatorandroid.Model.Response.TransferRest
import com.example.banksimulatorandroid.R
import kotlinx.android.synthetic.main.item_transfer.view.*
import java.lang.StringBuilder

class TransferListAdapter(var transfers: ArrayList<TransferRest>): RecyclerView.Adapter<TransferListAdapter.TransferViewHolder>() {

    fun updateTransfers(newTransfers: List<TransferRest>) {
        transfers.clear()
        transfers.addAll(newTransfers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TransferViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_transfer, parent, false)
    )

    override fun getItemCount() = transfers.size

    override fun onBindViewHolder(holder: TransferViewHolder, position: Int) {
        holder.bind(transfers[position])
    }

    class TransferViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val adapterUtils = AdapterUtils()

        val date = view.dateTxt
        val receiverFirstName = view.receiverFirstNameTxt
        val receiverLastName = view.receiverLastNameTxt
        val receiverAccountNumber = view.receiverAccountNumberTxt
        val transferredMoney = view.transferredMoneyTxt
        val transferDirection = view.transferDirectionImg

        fun bind(transfer: TransferRest) {

            date.text = adapterUtils.convertDate(transfer.date)
            receiverFirstName.text = transfer.receiverFirstName
            receiverLastName.text = transfer.receiverLastName
            receiverAccountNumber.text = adapterUtils.convertAccountNumber(transfer.receiverAccountNumber)
            transferredMoney.text = adapterUtils.convertTransferredMoney(transfer.transferredMoney)
        }
    }
}