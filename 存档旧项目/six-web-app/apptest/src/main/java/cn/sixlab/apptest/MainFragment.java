package cn.sixlab.apptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.cengalabs.flatui.FlatUI;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.sixlab.apptest.flatui.FlatActivity;

public class MainFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);

        Button button1 = (Button) view.findViewById(R.id.button1);
        Button button2 = (Button) view.findViewById(R.id.button2);
        Button button3 = (Button) view.findViewById(R.id.button3);
        Button button4 = (Button) view.findViewById(R.id.button4);
        Button button5 = (Button) view.findViewById(R.id.button5);
        Button button6 = (Button) view.findViewById(R.id.button6);
        Button button7 = (Button) view.findViewById(R.id.button7);
        Button button8 = (Button) view.findViewById(R.id.button8);
        Button button9 = (Button) view.findViewById(R.id.button9);

        button1.setOnClickListener(v -> {
            Log.e("sixlab","111");
            Intent intent = new Intent(getContext(), FlatActivity.class);
            getContext().startActivity(intent);
            Log.e("sixlab","111222");
        });
        button2.setOnClickListener(e->{

        });
        button3.setOnClickListener(e->{});
        button4.setOnClickListener(e->{});
        button5.setOnClickListener(e->{});
        button6.setOnClickListener(e->{});
        button7.setOnClickListener(e->{});
        button8.setOnClickListener(e->{});
        button9.setOnClickListener(e->{});

        Button btn1 = (Button) view.findViewById(R.id.btn1);
        Button btn2 = (Button) view.findViewById(R.id.btn2);
        Button btn3 = (Button) view.findViewById(R.id.btn3);
        Button btn4 = (Button) view.findViewById(R.id.btn4);
        Button btn5 = (Button) view.findViewById(R.id.btn5);
        Button btn6 = (Button) view.findViewById(R.id.btn6);
        Button btn7 = (Button) view.findViewById(R.id.btn7);
        Button btn8 = (Button) view.findViewById(R.id.btn8);
        Button btn9 = (Button) view.findViewById(R.id.btn9);

        btn1.setOnClickListener(e->{});
        btn2.setOnClickListener(e->{});
        btn3.setOnClickListener(e->{});
        btn4.setOnClickListener(e->{});
        btn5.setOnClickListener(e->{});
        btn6.setOnClickListener(e->{});
        btn7.setOnClickListener(e->{});
        btn8.setOnClickListener(e->{});
        btn9.setOnClickListener(e->{});
        return view;
    }
}
