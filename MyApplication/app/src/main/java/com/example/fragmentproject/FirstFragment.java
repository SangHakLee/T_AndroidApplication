package com.example.fragmentproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class FirstFragment extends Fragment {

    Context context;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context; // attach 될때 현재 context를 넣는다.
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }

    TextView tv;
    EditText et;
    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tv.setText(et.getText().toString()); // 에딧텍스트의 값은 tv로

//            switch (v.getId()){
//                case 1:
//                break;
//            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_first, null, false); // 어떤것 inflate 할건지, 부모 객체 필요없어서 null

        // view로 접근하면 onClick... r같은거 할수 있다.
        tv = (TextView)view.findViewById(R.id.showText);
        et = (EditText)view.findViewById(R.id.editText);
        view.findViewById(R.id.button).setOnClickListener(handler);

        return view; // 부모를 넘기는게 아니라 내가 만든 fragment_first를 inflate 해서 넘긴다.
    }
}