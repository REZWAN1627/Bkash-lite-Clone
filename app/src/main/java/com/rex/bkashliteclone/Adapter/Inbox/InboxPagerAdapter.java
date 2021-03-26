package com.rex.bkashliteclone.Adapter.Inbox;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rex.bkashliteclone.MainUI.FragmentUI.Inbox.Notifications.Notifications;
import com.rex.bkashliteclone.MainUI.FragmentUI.Inbox.Transactions.Transactions;
import com.rex.bkashliteclone.R;

public class InboxPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public InboxPagerAdapter(@NonNull FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new Notifications();
                break;
            case 1:
                fragment = new Transactions();
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
