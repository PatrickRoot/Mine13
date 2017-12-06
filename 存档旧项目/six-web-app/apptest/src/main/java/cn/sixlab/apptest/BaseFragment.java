package cn.sixlab.apptest;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

    public void fabClick(){
        Snackbar.make(getView(), "Hello", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
