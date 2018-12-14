package com.example.laptracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    public static Set set;
    public static Workout w;
    public static int yardage;

    Fragment currentFragment = null;
    FragmentTransaction ft;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_add:
                    currentFragment = new AddFragment();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.container, currentFragment);
                    ft.commit();
                    return true;
                case R.id.navigation_view:
                    currentFragment = new ViewFragment();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.container, currentFragment);
                    ft.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        set = new Set();
        w = new Workout();
        yardage = 0;


        ft = getSupportFragmentManager().beginTransaction();
        currentFragment = new AddFragment();
        ft.replace(R.id.container, currentFragment);
        ft.commit();


        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
