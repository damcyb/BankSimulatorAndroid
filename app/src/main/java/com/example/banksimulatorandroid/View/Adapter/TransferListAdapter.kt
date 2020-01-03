package com.example.banksimulatorandroid.View.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.banksimulatorandroid.Model.Response.TransferRest
import com.example.banksimulatorandroid.R
import kotlinx.android.synthetic.main.item_transfer.view.*

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

        private val adapterUtils = AdapterUtils()

        private val date = view.dateTxt
        private val receiverFirstName = view.receiverFirstNameTxt
        private val receiverLastName = view.receiverLastNameTxt
        private val receiverAccountNumber = view.receiverAccountNumberTxt
        private val transferredMoney = view.transferredMoneyTxt
        private val transferDirection = view.transferDirectionImg

        fun bind(transfer: TransferRest) {

            date.text = adapterUtils.convertDate(transfer.date)
            receiverFirstName.text = transfer.firstName
            receiverLastName.text = transfer.lastName
            receiverAccountNumber.text = adapterUtils.convertAccountNumber(transfer.accountNumber)
            transferredMoney.text = adapterUtils.convertTransferredMoney(transfer.transferredMoney)
            val color = adapterUtils.convertTransferDirection(transfer.transferDirection)
            transferDirection.setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN)
        }
    }
}