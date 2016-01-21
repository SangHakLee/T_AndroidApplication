package com.example.fragmentproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FirstFragment extends Fragment {

    Context context;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, null, false); // 어떤것 inflate 할건지, 부모 객체 필요없어서 null
//        return super.onCreateView(inflater, container, savedInstanceState);
        return view; // 부모를 넘기는게 아니라 내가 만든 fragment_first를 inflate 해서 넘긴다.
    }
}
