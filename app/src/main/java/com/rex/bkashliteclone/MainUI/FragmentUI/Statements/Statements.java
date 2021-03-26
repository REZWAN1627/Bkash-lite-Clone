package com.rex.bkashliteclone.MainUI.FragmentUI.Statements;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.rex.bkashliteclone.Adapter.Statements.StatementsPagerAdapter;
import com.rex.bkashliteclone.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Statements#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Statements extends Fragment {

    private View view;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private StatementsPagerAdapter pagerAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Statements() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Statements.
     */
    // TODO: Rename and change types and number of parameters
    public static Statements newInstance(String param1, String param2) {
        Statements fragment = new Statements();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_statements, container, false);
        viewPager = view.findViewById(R.id.statement_viewpager);
        tabLayout = view.findViewById(R.id.tabLayout_statements);

        pagerAdapter = new StatementsPagerAdapter(getChildFragmentManager(),getContext());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager,true);


        return view;
    }
}