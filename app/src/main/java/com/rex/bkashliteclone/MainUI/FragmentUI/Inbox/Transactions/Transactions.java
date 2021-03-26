package com.rex.bkashliteclone.MainUI.FragmentUI.Inbox.Transactions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rex.bkashliteclone.Adapter.Inbox.TransactionRecyclerViewAdapter;
import com.rex.bkashliteclone.Model.Inbox.TransactionModel;
import com.rex.bkashliteclone.R;

import java.util.ArrayList;


public class Transactions extends Fragment {
    private View view;
    private ArrayList<TransactionModel> transactionModels = new ArrayList<>();
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_transactions, container, false);
        recyclerView = view.findViewById(R.id.inbox_transaction_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        transactionModels.add(new TransactionModel("Cash out", "01738567818", "rrroopfd8k", "11 / 2 / 20",
                "-10000"));
        transactionModels.add(new TransactionModel("Cash out", "01738567818", "rrroopfd8k", "11 / 2 / 20",
                "-10000"));
        transactionModels.add(new TransactionModel("Cash out", "01738567818", "rrroopfd8k", "11 / 2 / 20",
                "-10000"));
        transactionModels.add(new TransactionModel("Cash out", "01738567818", "rrroopfd8k", "11 / 2 / 20",
                "-10000"));
        transactionModels.add(new TransactionModel("Cash out", "01738567818", "rrroopfd8k", "11 / 2 / 20",
                "-10000"));
        transactionModels.add(new TransactionModel("Cash out", "01738567818", "rrroopfd8k", "11 / 2 / 20",
                "-10000"));
        transactionModels.add(new TransactionModel("Cash out", "01738567818", "rrroopfd8k", "11 / 2 / 20",
                "-10000"));
        transactionModels.add(new TransactionModel("Cash out", "01738567818", "rrroopfd8k", "11 / 2 / 20",
                "-10000"));
        transactionModels.add(new TransactionModel("Cash out", "01738567818", "rrroopfd8k", "11 / 2 / 20",
                "-10000"));
        transactionModels.add(new TransactionModel("Cash out", "01738567818", "rrroopfd8k", "11 / 2 / 20",
                "-10000"));

        recyclerView.setAdapter(new TransactionRecyclerViewAdapter(transactionModels));

        return view;
    }
}