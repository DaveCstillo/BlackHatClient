package com.example.vidalgt.blackhatclient;

import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

    public BaseActivity getBaseActivity(){
        return (BaseActivity)this.getActivity();
    }
}
