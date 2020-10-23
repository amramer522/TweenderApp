package com.myapplication.ui.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.myapplication.R;
import com.myapplication.helper.HelperMethod;
import com.myapplication.ui.fragment.splachCycle.SliderFragment;
import com.myapplication.ui.fragment.splachCycle.SplashFragment;
import com.myapplication.ui.fragment.userCycle.ChoosePersonalImageFragment;

import static com.myapplication.helper.HelperMethod.replace;

public class SplashCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_cycle);
        SplashFragment splashFragment = new SplashFragment();
        replace(splashFragment, getSupportFragmentManager(), R.id.Splash_Cycle_Activity_Fl_Container);

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
