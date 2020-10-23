package com.myapplication.ui.fragment.splachCycle;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.R;
import com.myapplication.ui.activity.HomeCycleActivity;
import com.myapplication.ui.activity.UserCycleActivity;

import static com.myapplication.helper.HelperMethod.replace;

public class SplashFragment extends Fragment {

    private SharedPreferences preferences;


    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        preferences = getActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);

//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("user_ID", "");
//        editor.putString("name","");
//        editor.putString("password","");
//        editor.putBoolean("status",false);
//        editor.commit();
        final boolean status = preferences.getBoolean("status", false);
        final String name = preferences.getString("name", "");


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (status) {
                    startActivity(new Intent(getActivity(), HomeCycleActivity.class));
                    getActivity().finish();

                } else {
                    if (name == null) {
                        ChooseLanguageFragment chooseLanguageFragment = new ChooseLanguageFragment();
                        replace(chooseLanguageFragment, getActivity().getSupportFragmentManager(), R.id.Splash_Cycle_Activity_Fl_Container);

                    } else {
                        startActivity(new Intent(getActivity(), UserCycleActivity.class));
                        getActivity().finish();
                    }
                }
            }
        }, 2000);

        return view;
    }


}
