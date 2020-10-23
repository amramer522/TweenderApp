package com.myapplication.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.myapplication.R;
import com.myapplication.helper.HelperMethod;
import com.myapplication.ui.fragment.userCycle.ChoosePersonalImageFragment;
import com.myapplication.ui.fragment.userCycle.LoginFragment;

import static com.myapplication.helper.HelperMethod.replace;

public class UserCycleActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cycle);

        LoginFragment loginFragment = new LoginFragment();
        replace(loginFragment,getSupportFragmentManager(),R.id.User_Cycle_Activity_Fl_Container);

    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
