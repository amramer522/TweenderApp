package com.myapplication.ui.fragment.homeCycle;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.myapplication.R;
import com.myapplication.adapter.FilteredRecycleViewAdapter;
import com.myapplication.data.model.Category;
import com.myapplication.data.model.Tweet;
import com.myapplication.data.model.User;
import com.myapplication.data.model.Word;
import com.myapplication.helper.DBConnection;
import com.myapplication.helper.HelperMethod;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.myapplication.helper.HelperMethod.getTextFromEt;
import static com.myapplication.helper.HelperMethod.showToastMsg;

public class HomeFragment extends Fragment {


    @BindView(R.id.Home_Fragment_Rv_Recycler)
    RecyclerView HomeFragmentRvRecycler;
    Unbinder unbinder;
    @BindView(R.id.Home_Fragment_Srl_Swipe)
    SwipeRefreshLayout HomeFragmentSrlSwipe;

    private FilteredRecycleViewAdapter adapter;
    private DBConnection db;
    private User user = new User();
    Tweet tweet = new Tweet();
    Date date = new Date();
    private SharedPreferences preferences;
    private ArrayList<Tweet> allTweets = new ArrayList<>();
    private ArrayList<Tweet> filteredTweets = new ArrayList<>();


    //
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        db = new DBConnection(getContext());
//        db.setIntialTweets();

        // المفروض اجيب بتاع اليوزر اللى فاتح
        preferences = getActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
        String userId = preferences.getString("user_ID", "");

        ArrayList<Category> allCategories = db.getAllCategories(userId);
//        showToastMsg(getContext(), "size of categories : " + allCategories.size());
        allTweets = db.getAllTweets();


        for (int i = 0; i < allTweets.size(); i++)
        {
            for (int j = 0; j < allCategories.size(); j++)
            {
                if (TextUtils.equals(allTweets.get(i).getTweet_category(),allCategories.get(j).getCategory()))
                {
                    Log.i("cat", "tweet number"+i+" category "+allTweets.get(i).getTweet_category()+" filter category "+allCategories.get(j).getCategory());
                    allTweets.remove(i);
                   break;
                }

            }

        }




//        showToastMsg(getContext(), "size of big array list : " + allTweets.size());
        HomeFragmentRvRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new FilteredRecycleViewAdapter(getActivity());
        adapter.setData(allTweets);
        HomeFragmentRvRecycler.setAdapter(adapter);

        HomeFragmentSrlSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HomeFragmentSrlSwipe.setRefreshing(false);
                    }
                }, 2000);
            }
        });


        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
