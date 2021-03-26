package com.rex.bkashliteclone.MainUI.FragmentUI.Toolbar;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rex.bkashliteclone.ClassInterface_Fragment.OnClickedFragment;
import com.rex.bkashliteclone.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EveryPageToolbar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EveryPageToolbar extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String headerTitle = "Text_Header";

    // TODO: Rename and change types of parameters
    private String HeaderTitle;
    private String mParam2;

    // variable initialize
    private TextView HeaderTextView;
    private OnClickedFragment listener;

    public EveryPageToolbar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param TextHeader Parameter 1.
     * @return A new instance of fragment EveryPageToolbar.
     */
    // TODO: Rename and change types and number of parameters
    public static EveryPageToolbar newInstance(String TextHeader) {
        EveryPageToolbar fragment = new EveryPageToolbar();
        Bundle args = new Bundle();
        args.putString(headerTitle, TextHeader);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            HeaderTitle = getArguments().getString(headerTitle);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_every_page_toolbar, container, false);

        HeaderTextView = view.findViewById(R.id.FragmentTollbarTextVies);

        HeaderTextView.setText(HeaderTitle);


        view.findViewById(R.id.EveryPageToolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.EveryPageNavClicked();
            }
        });


        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnClickedFragment){
            listener = (OnClickedFragment) context;
        }else {
            throw new ClassCastException(context.toString()+"Must Implements Method");
        }
    }
}