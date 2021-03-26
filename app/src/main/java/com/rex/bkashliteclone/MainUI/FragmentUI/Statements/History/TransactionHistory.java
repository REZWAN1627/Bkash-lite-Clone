package com.rex.bkashliteclone.MainUI.FragmentUI.Statements.History;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rex.bkashliteclone.Adapter.Statements.StatementsRecyclerViewAdapter;
import com.rex.bkashliteclone.Model.Inbox.TransactionModel;
import com.rex.bkashliteclone.Model.Statements.TransactionHistoryModel;
import com.rex.bkashliteclone.R;

import java.util.ArrayList;


public class TransactionHistory extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private ArrayList<TransactionHistoryModel> transactionHistoryModels = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_transaction_history, container, false);
        recyclerView = view.findViewById(R.id.statements_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        transactionHistoryModels.add(new TransactionHistoryModel("Cash out", "01738567818", "rrroopfd8k", "11 / 2 / 20",
                "-10000"));
        transactionHistoryModels.add(new TransactionHistoryModel("Cash out", "01738567818", "rrroopfd8k", "11 / 2 / 20",
                "-10000"));
        transactionHistoryModels.add(new TransactionHistoryModel("Cash out", "01738567818", "rrroopfd8k", "11 / 2 / 20",
                "-10000"));
        transactionHistoryModels.add(new TransactionHistoryModel("Cash out", "01738567818", "rrroopfd8k", "11 / 2 / 20",
                "-10000"));
        transactionHistoryModels.add(new TransactionHistoryModel("Cash out", "01738567818", "rrroopfd8k", "11 / 2 / 20",
                "-10000"));
        transactionHistoryModels.add(new TransactionHistoryModel("Cash out", "01738567818", "rrroopfd8k", "11 / 2 / 20",
                "-10000"));
        transactionHistoryModels.add(new TransactionHistoryModel("Cash out", "01738567818", "rrroopfd8k", "11 / 2 / 20",
                "-10000"));
        transactionHistoryModels.add(new TransactionHistoryModel("Cash out", "01738567818", "rrroopfd8k", "11 / 2 / 20",
                "-10000"));
        transactionHistoryModels.add(new TransactionHistoryModel("Cash out", "01738567818", "rrroopfd8k", "11 / 2 / 20",
                "-10000"));
        transactionHistoryModels.add(new TransactionHistoryModel("Cash out", "01738567818", "rrroopfd8k", "11 / 2 / 20",
                "-10000"));

        recyclerView.setAdapter(new StatementsRecyclerViewAdapter(transactionHistoryModels));

        return view;
    }
}