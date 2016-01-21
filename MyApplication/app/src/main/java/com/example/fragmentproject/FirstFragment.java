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

    // 번들에서 가져와서 값 대입 해주기위한 멤버 필드
    int cnt;
    String name;


    public FirstFragment() {
        // Required empty public constructor

    // 여기서 했더니 nullpoint 뜸. onCreateView 로 이동
//        Bundle bundle = getArguments(); // main에서 던진 bundle 받음
//        cnt = bundle.getInt("cnt");
//        name = bundle.getString("name");
    }

    // 번들 대신 방법.
    public void setData(String name, int cnt){
        this.name = name;
        this.cnt = cnt;
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

        // 여기서도 번들을 해야한다. 맨처음 뜨는곳이 여기이기 때문에
//        Bundle bundle = getArguments(); // main에서 던진 bundle 받음
//        cnt = bundle.getInt("cnt");
//        name = bundle.getString("name");

        et.setText(name + cnt); // 번들로 받아와서 멤버에 할당한 변수들

        return view; // 부모를 넘기는게 아니라 내가 만든 fragment_first를 inflate 해서 넘긴다.
    }
}
