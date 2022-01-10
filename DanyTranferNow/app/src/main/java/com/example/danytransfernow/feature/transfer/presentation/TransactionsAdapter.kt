package com.example.danytransfernow.feature.transfer.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.danytransfernow.databinding.ListItemBinding
import com.example.danytransfernow.feature.transfer.domain.model.Transaction

class TransactionsAdapter :
    RecyclerView.Adapter<CustomViewHolder>() {

    var trananctions = mutableListOf<Transaction>()
    fun setList(list: List<Transaction>) {
        this.trananctions = list as MutableList<Transaction>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ListItemBinding.inflate(inflater, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val transaction = trananctions[position]
        if (transaction.transactionType == "received") {
            holder.binding.textViewName.text = transaction.sender?.accountHolder ?: ""
            holder.binding.textViewAccoutNo.text = transaction.sender?.accountNo ?: ""
        } else {
            holder.binding.textViewName.text = transaction.receipient?.accountHolder ?: ""
            holder.binding.textViewAccoutNo.text = transaction.receipient?.accountNo ?: ""
        }

        holder.binding.textViewAccoutNo.text = transaction.transactionId
        holder.binding.textViewAmount.text = transaction.amount.toString()
    }

    override fun getItemCount(): Int {
        return trananctions.size
    }


}

class CustomViewHolder(var binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)
