package com.myapplication;

import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class children extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static children newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        children fragment = new children();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_children, container, false);
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvTitle.setText("Fragment #" + mPage);
        return view;
    }

}
