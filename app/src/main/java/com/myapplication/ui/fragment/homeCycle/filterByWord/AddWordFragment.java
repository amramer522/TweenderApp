package com.myapplication.ui.fragment.homeCycle.filterByWord;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.myapplication.R;
import com.myapplication.data.model.Word;
import com.myapplication.helper.DBConnection;
import com.myapplication.helper.HelperMethod;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.myapplication.helper.HelperMethod.getTextFromSpinner;
import static com.myapplication.helper.HelperMethod.getTextFromTil;
import static com.myapplication.helper.HelperMethod.replace;
import static com.myapplication.helper.HelperMethod.showToastMsg;

public class AddWordFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.Add_Word_Fragment_Sp_Categories)
    Spinner AddWordFragmentSpCategories;
    @BindView(R.id.Add_Word_Fragment_Til_Annoying_Word)
    TextInputLayout AddWordFragmentTilAnnoyingWord;
    private DBConnection db;
    private SharedPreferences preferences;

    public AddWordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_word, container, false);
        unbinder = ButterKnife.bind(this, view);
        db = new DBConnection(getContext());

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.Add_Word_Fragment_Btn_Add, R.id.Add_Word_Fragment_Tv_Back_To_Words_List})
    public void onViewClicked(View view) {
        FilteredWordsListFragment wordsListFragment = new FilteredWordsListFragment();
        preferences = getActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
        String userId = preferences.getString("user_ID", "");
        switch (view.getId()) {
            case R.id.Add_Word_Fragment_Btn_Add:
                String word_string = getTextFromTil(AddWordFragmentTilAnnoyingWord);
                String category = getTextFromSpinner(AddWordFragmentSpCategories);
                if (!TextUtils.isEmpty(word_string))
                {
                    if (!db.checkwordExist(getContext(), word_string, category,userId))
                    {

                        Word word = new Word(word_string, category,userId);
                        db.insertWord(getContext(), word);
                        replace(wordsListFragment, getActivity().getSupportFragmentManager(), R.id.Content_Home_Cycle_Fragment_Container);

                    }
//                    if (!db.checkwordExist(getContext(), word_string,userId))
//                    {
//
//                        Word word = new Word(word_string,userId);
//                        db.insertWord(getContext(), word);
//                        replace(wordsListFragment, getActivity().getSupportFragmentManager(), R.id.Content_Home_Cycle_Fragment_Container);
//
//                    }
                    else {
                        showToastMsg(getContext(), "word of this category exist before");
                    }
                } else {
                    showToastMsg(getContext(), "Please enter the annoying word");
                }
                break;
            case R.id.Add_Word_Fragment_Tv_Back_To_Words_List:
                replace(wordsListFragment, getActivity().getSupportFragmentManager(), R.id.Content_Home_Cycle_Fragment_Container);
                break;
        }
    }
}
