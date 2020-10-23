package com.myapplication.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.myapplication.R;
import com.myapplication.data.model.User;
import com.myapplication.helper.DBConnection;
import com.myapplication.helper.HelperMethod;
import com.myapplication.ui.fragment.homeCycle.ProfileFragment;

import java.util.ResourceBundle;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.myapplication.helper.HelperMethod.replace;

public class DetailesActivity extends AppCompatActivity {


    @BindView(R.id.Details_Activity_Tb_ToolBar)
    Toolbar DetailsActivityTbToolBar;
    private DBConnection db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailes);
        ButterKnife.bind(this);
        db = new DBConnection(this);


        setSupportActionBar(DetailsActivityTbToolBar);


        DetailsActivityTbToolBar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        DetailsActivityTbToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeCycleActivity.class));
                finish();
            }
        });

        ProfileFragment profileFragment = new ProfileFragment();
        replace(profileFragment, getSupportFragmentManager(), R.id.Details_Activity_Fl_Container);

    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(DetailesActivity.this, HomeCycleActivity.class));

    }
}
