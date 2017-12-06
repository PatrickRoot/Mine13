package cn.sixlab.sixlab.web.app.ft;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cn.sixlab.sixlab.web.app.R;
import cn.sixlab.sixlab.web.app.util.BaseFragment;

public class MainFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        Toast toast = Toast.makeText(getActivity(), "111", Toast.LENGTH_SHORT);
        toast.show();

        return view;
    }

}
