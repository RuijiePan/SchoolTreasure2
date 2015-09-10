package com.myteam.activity.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myteam.activity.R;

/**
 * Created by yum on 2015/9/8.
 */
public class MyShareFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null)
            return  null;
        return inflater.inflate(R.layout.layout_share,container,false);
    }
}
