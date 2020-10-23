package com.myapplication.ui.fragment.homeCycle.filterByCategory;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.myapplication.R;
import com.myapplication.adapter.CategoryFilterAdapter;
//import com.myapplication.data.model.filteredCategory.FilteredCategoryList;
import com.myapplication.data.model.Category;
import com.myapplication.data.rest.ApiServices;
import com.myapplication.data.rest.RetrofitClient;
import com.myapplication.helper.DBConnection;
import com.myapplication.helper.HelperMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myapplication.helper.HelperMethod.replace;

public class FilteredCategoriesListFragment extends Fragment {


    @BindView(R.id.Filtered_Categories_List_Rv_Recycler)
    RecyclerView FilteredCategoriesListRvRecycler;
    Unbinder unbinder;
    private DBConnection db;
    private SharedPreferences preferences;


    public FilteredCategoriesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filtered_categories_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        db = new DBConnection(getContext());
        preferences = getActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
        String userId = preferences.getString("user_ID", "");
        ArrayList<Category> allCategories = db.getAllCategories(userId);
        CategoryFilterAdapter adapter = new CategoryFilterAdapter(getActivity());
        adapter.setData(allCategories);
        FilteredCategoriesListRvRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        FilteredCategoriesListRvRecycler.setAdapter(adapter);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.Filtered_Categories_List_Fab_Add)
    public void onViewClicked()
    {
        AddCategoryFragment addCategoryFragment = new AddCategoryFragment();
        replace(addCategoryFragment, getActivity().getSupportFragmentManager(), R.id.Content_Home_Cycle_Fragment_Container);
    }
}
