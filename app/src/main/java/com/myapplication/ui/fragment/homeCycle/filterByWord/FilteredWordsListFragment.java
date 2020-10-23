package com.myapplication.ui.fragment.homeCycle.filterByWord;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.R;
import com.myapplication.adapter.WordFilterAdapter;
import com.myapplication.data.model.Word;
import com.myapplication.helper.DBConnection;
import com.myapplication.helper.HelperMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.myapplication.helper.HelperMethod.replace;


public class FilteredWordsListFragment extends Fragment {

    List<String> wordsList = new ArrayList<>();
    @BindView(R.id.Filtered_Words_List_Rv_Recycler)
    RecyclerView FilteredWordsListRvRecycler;
    Unbinder unbinder;
    private DBConnection db;
    private SharedPreferences preferences;

    public FilteredWordsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filtered_words_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        db = new DBConnection(getContext());
        preferences = getActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
        String userId = preferences.getString("user_ID", "");
        ArrayList<Word> allWords = db.getAllWords(userId);
        WordFilterAdapter adapter = new WordFilterAdapter(getActivity());
        adapter.setData(allWords);
        FilteredWordsListRvRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        FilteredWordsListRvRecycler.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.Filtered_Words_List_Fab_Add)
    public void onViewClicked()
    {
        AddWordFragment addWordFragment = new AddWordFragment();
        replace(addWordFragment, getActivity().getSupportFragmentManager(), R.id.Content_Home_Cycle_Fragment_Container);
    }


}
