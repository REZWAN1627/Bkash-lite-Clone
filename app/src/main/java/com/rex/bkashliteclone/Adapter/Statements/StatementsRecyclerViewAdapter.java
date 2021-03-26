package com.rex.bkashliteclone.Adapter.Statements;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rex.bkashliteclone.Model.Statements.TransactionHistoryModel;
import com.rex.bkashliteclone.R;

import java.util.ArrayList;

public class StatementsRecyclerViewAdapter extends RecyclerView.Adapter<StatementsRecyclerViewAdapter.StatementsRecyclerViewHolder> {

    private ArrayList<TransactionHistoryModel> transactionHistoryModels = new ArrayList<>();

    public StatementsRecyclerViewAdapter(ArrayList<TransactionHistoryModel> transactionHistoryModels) {
        this.transactionHistoryModels = transactionHistoryModels;
    }

    @NonNull
    @Override
    public StatementsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_statements_history, parent, false);
        return new StatementsRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatementsRecyclerViewHolder holder, int position) {
        holder.transactionProcess.setText(transactionHistoryModels.get(position).getTransactionProcessName());
        holder.transactionOwnerNumber.setText(transactionHistoryModels.get(position).getTransactionOwnerNumber());
        holder.transactionID.setText(transactionHistoryModels.get(position).getTransactionID());
        holder.transactionDate.setText(transactionHistoryModels.get(position).getTransactionDate());
        holder.transactionAmount.setText(transactionHistoryModels.get(position).getTransactionAmount());

    }

    @Override
    public int getItemCount() {
        return transactionHistoryModels.size();
    }

    public class StatementsRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView transactionProcess, transactionOwnerNumber, transactionID, transactionDate, transactionAmount;

        public StatementsRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            transactionAmount = itemView.findViewById(R.id.transaction_amount_statements);
            transactionProcess = itemView.findViewById(R.id.transaction_process_statement);
            transactionOwnerNumber = itemView.findViewById(R.id.transaction_owner_number_statements);
            transactionID = itemView.findViewById(R.id.transaction_ID_statements);
            transactionDate = itemView.findViewById(R.id.transaction_date_statements);
        }
    }
}
