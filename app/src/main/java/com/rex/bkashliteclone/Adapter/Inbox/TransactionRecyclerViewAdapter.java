package com.rex.bkashliteclone.Adapter.Inbox;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rex.bkashliteclone.Model.Inbox.TransactionModel;
import com.rex.bkashliteclone.R;

import java.util.ArrayList;

public class TransactionRecyclerViewAdapter extends RecyclerView.Adapter<TransactionRecyclerViewAdapter.TransactionRecyclerViewHolder> {
    private ArrayList<TransactionModel> transactionModels = new ArrayList<>();

    public TransactionRecyclerViewAdapter(ArrayList<TransactionModel> transactionModels) {
        this.transactionModels = transactionModels;
    }

    @NonNull
    @Override
    public TransactionRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_inbox_transactions, parent, false);
        return new TransactionRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionRecyclerViewHolder holder, int position) {
        holder.transactionProcess.setText(transactionModels.get(position).getTransactionProcessName());
        holder.transactionOwnerNumber.setText(transactionModels.get(position).getTransactionOwnerNumber());
        holder.transactionID.setText(transactionModels.get(position).getTransactionID());
        holder.transactionDate.setText(transactionModels.get(position).getTransactionDate());
        holder.transactionAmount.setText(transactionModels.get(position).getTransactionAmount());

    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    public class TransactionRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView transactionProcess, transactionOwnerNumber, transactionID, transactionDate, transactionAmount;
        public TransactionRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            transactionAmount = itemView.findViewById(R.id.transaction_amount);
            transactionProcess = itemView.findViewById(R.id.transaction_process);
            transactionOwnerNumber = itemView.findViewById(R.id.transaction_owner_number);
            transactionID = itemView.findViewById(R.id.transaction_ID);
            transactionDate = itemView.findViewById(R.id.transaction_date);
        }
    }
}
