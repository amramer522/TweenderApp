package com.myapplication.ui.fragment.homeCycle.filterByCategory;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.myapplication.R;
//import com.myapplication.data.model.filteredCategory.FilteredCategoryList;
import com.myapplication.data.model.Category;
import com.myapplication.data.model.Word;
import com.myapplication.data.rest.ApiServices;
import com.myapplication.data.rest.RetrofitClient;
import com.myapplication.helper.DBConnection;
import com.myapplication.helper.HelperMethod;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myapplication.helper.HelperMethod.getTextFromSpinner;
import static com.myapplication.helper.HelperMethod.replace;
import static com.myapplication.helper.HelperMethod.showToastMsg;

public class AddCategoryFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.Add_Category_Fragment_Sp_Categories)
    Spinner AddCategoryFragmentSpCategories;
    private DBConnection db;
    private SharedPreferences preferences;


    public AddCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_category, container, false);
        unbinder = ButterKnife.bind(this, view);
        db = new DBConnection(getContext());

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.Add_Category_Fragment_Btn_Add, R.id.Add_Category_Fragment_Tv_Back_To_Categories_List})
    public void onViewClicked(View view) {
        FilteredCategoriesListFragment filteredCategoriesListFragment = new FilteredCategoriesListFragment();
        preferences = getActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
        String userId = preferences.getString("user_ID", "");
        switch (view.getId()) {
            case R.id.Add_Category_Fragment_Btn_Add:
                String category_string = getTextFromSpinner(AddCategoryFragmentSpCategories);
                if (!db.checkCategoryExist(getContext(), category_string,userId))
                {
                    //  ال id بتاع اللى عامل login
                    Category category = new Category(category_string,userId);
                    db.insertCategory(getContext(), category);
                    replace(filteredCategoriesListFragment, getActivity().getSupportFragmentManager(), R.id.Content_Home_Cycle_Fragment_Container);

                } else {
                    showToastMsg(getContext(), "this category exist before");
                }
                break;
            case R.id.Add_Category_Fragment_Tv_Back_To_Categories_List:
                replace(filteredCategoriesListFragment, getActivity().getSupportFragmentManager(), R.id.Content_Home_Cycle_Fragment_Container);
                break;
        }
    }


}
