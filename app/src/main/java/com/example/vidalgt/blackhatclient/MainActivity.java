package com.example.vidalgt.blackhatclient;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

public class MainActivity extends BaseActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    notificationContentFragment notif = new notificationContentFragment();
                    changeFragment(notif);
                    return true;
                case R.id.navigation_products:
                    foodContainer f = new foodContainer();
                    changeFragment(f);
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
        notificationContentFragment notif = new notificationContentFragment();
        changeFragment(notif);


    }

}
