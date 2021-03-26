package com.rex.bkashliteclone.Adapter.Statements;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rex.bkashliteclone.MainUI.FragmentUI.Statements.History.TransactionHistory;
import com.rex.bkashliteclone.MainUI.FragmentUI.Statements.Summary.TransactionSummary;
import com.rex.bkashliteclone.R;

public class StatementsPagerAdapter extends FragmentPagerAdapter {
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_3, R.string.tab_text_4};
    private final Context mContext;

    public StatementsPagerAdapter(@NonNull FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new TransactionHistory();
                break;
            case 1:
                fragment = new TransactionSummary();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }
}
