package com.myapplication.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myapplication.R;
import com.myapplication.data.model.User;
import com.myapplication.helper.DBConnection;
import com.myapplication.helper.HelperMethod;
import com.myapplication.ui.fragment.homeCycle.HomeFragment;
import com.myapplication.ui.fragment.homeCycle.ProfileFragment;
import com.myapplication.ui.fragment.homeCycle.filterByCategory.FilteredCategoriesListFragment;
import com.myapplication.ui.fragment.homeCycle.filterByWord.FilteredWordsListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


import static com.myapplication.helper.HelperMethod.replace;

public class HomeCycleActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.Content_Home_Cycle_Fragment_Container)
    FrameLayout ContentHomeCycleFragmentContainer;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private SharedPreferences preferences;
    private User user = new User();
    private DBConnection db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cycle);
        ButterKnife.bind(this);
        db = new DBConnection(this);
        preferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        String userId = preferences.getString("user_ID", "");

        user = db.getUserObject(this, userId);

        toolbar.setTitle(R.string.home);
        setSupportActionBar(toolbar);

        HomeFragment homeFragment = new HomeFragment();
        replace(homeFragment, getSupportFragmentManager(), R.id.Content_Home_Cycle_Fragment_Container);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(this);
        navHeader();

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
            finish();
        }

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            toolbar.setTitle(getString(R.string.home));
            HomeFragment homeFragment = new HomeFragment();
//            homeFragment.setCurrentUser(user);
            replace(homeFragment, getSupportFragmentManager(), R.id.Content_Home_Cycle_Fragment_Container);
        } else if (id == R.id.filtered_categories) {
            toolbar.setTitle(R.string.filtered_categories);
            FilteredCategoriesListFragment categoriesListFragment = new FilteredCategoriesListFragment();
            replace(categoriesListFragment, getSupportFragmentManager(), R.id.Content_Home_Cycle_Fragment_Container);
        } else if (id == R.id.filtered_words) {
            toolbar.setTitle(R.string.filtered_words);
            FilteredWordsListFragment wordsListFragment = new FilteredWordsListFragment();
            replace(wordsListFragment, getSupportFragmentManager(), R.id.Content_Home_Cycle_Fragment_Container);
        } else if (id == R.id.exit) {

            preferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("status", false);
            editor.putString("user_ID", "");
            editor.commit();
            startActivity(new Intent(HomeCycleActivity.this, UserCycleActivity.class));
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void navHeader() {
        View headerview = navView.inflateHeaderView(R.layout.nav_header_home_cycle);
        TextView user_name = headerview.findViewById(R.id.Nav_Header_Home_Cycle_Tv_User_Name);
        TextView user_email = headerview.findViewById(R.id.Nav_Header_Home_Cycle_Tv_User_Email);

        if (user!=null)
        {
            user_name.setText(user.getUname());
            user_email.setText(user.getUemail());
        }

    }


}
