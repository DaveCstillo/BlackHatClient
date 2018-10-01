package com.example.vidalgt.blackhatclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.vidalgt.blackhatclient.serverconnection.Images;
import com.example.vidalgt.blackhatclient.serverconnection.httpHandler;

public class MainActivity extends BaseActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Notificaciones notif = new Notificaciones();
                    changeFragment(notif);
                    return true;
                case R.id.navigation_products:
                    foodContainer f = new foodContainer();
                    changeFragment(f);
                    return true;
                case R.id.navigation_map:
                    Intent intent = new Intent(getActivity(), MapsActivity.class);

                    startActivity(intent);
                    return true;
                case R.id.navigation_reservations:
                    Reservaciones reservaciones = new Reservaciones();
                    changeFragment(reservaciones);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        httpHandler.instance = new httpHandler("https://cafebarsite.000webhostapp.com/");
        Images.init();


    }

}
