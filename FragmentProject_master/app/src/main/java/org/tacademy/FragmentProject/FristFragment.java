package org.tacademy.FragmentProject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class FristFragment extends Fragment {
    Context context;
    int cnt;
    String name;
    public FristFragment(){

    }
    public void setData(String name, int cnt){
        this.name = name;
        this.cnt = cnt;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    TextView tv;
    EditText et;
    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button :
                    tv.setText(et.getText().toString());
                    break;
                case R.id.button6 :
                    doAction1();
                    break;
                case R.id.button7 :
                    doAction2();
                    break;
            }

        }
    };

    interface MyListener{
        void receiveMessage(String data);
    }
    MyListener listener;
    public void setOnMyListener(MyListener listener){
        this.listener = listener;
    }

    void doAction2(){
        if(listener != null){
            listener.receiveMessage(et.getText().toString());
        }
//        ((MainActivity)context).dochangeData(et.getText().toString());
    }
    void doAction1(){
        Intent intent = new Intent(context, MyActiivty.class);
        startActivity(intent);
        context.startService(intent);
    }

    void aa(){
        getView().findViewById(R.id.button);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frist, null, false);
        tv = (TextView)view.findViewById(R.id.showText);
        et = (EditText)view.findViewById(R.id.editText);
        view.findViewById(R.id.button).setOnClickListener(handler);
        view.findViewById(R.id.button6).setOnClickListener(handler);
        view.findViewById(R.id.button7).setOnClickListener(handler);
        Bundle bundle = getArguments();
        cnt  = bundle.getInt("cnt");
        name = bundle.getString("name");
        et.setText(name + cnt);


        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }
}
