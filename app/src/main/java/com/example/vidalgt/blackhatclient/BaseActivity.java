package com.example.vidalgt.blackhatclient;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.vidalgt.blackhatclient.Content.feedContent;
import com.example.vidalgt.blackhatclient.Content.foodInfo;
import com.example.vidalgt.blackhatclient.dummy.DummyContent;

public class BaseActivity extends AppCompatActivity  implements foodDisplayFragment.OnListFragmentInteractionListener, notificationContentFragment.OnListFragmentInteractionListener{



    public FragmentManager manager = getSupportFragmentManager();
    public void changeFragment(BaseFragment f){
        changeFragment(f,true);
    }

    public void changeFragment(BaseFragment f, boolean backstack){
        FragmentTransaction trans = manager.beginTransaction().replace(R.id.fragmentContenido,f);
        if(backstack)
            trans.addToBackStack(null);
        else
            manager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);

        trans.commit();

    }

    public AppCompatActivity getActivity(){return this;}

    public FragmentManager getManager() {
        return manager;
    }

    @Override
    public void onListFragmentInteraction(foodInfo.foodItem item) {

    }

    @Override
    public void onListFragmentInteraction(feedContent.feedItem item) {

    }
}
