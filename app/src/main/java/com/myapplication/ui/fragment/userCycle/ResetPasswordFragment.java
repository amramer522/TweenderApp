package com.myapplication.ui.fragment.userCycle;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.R;
import com.myapplication.helper.HelperMethod;
import com.myapplication.ui.activity.HomeCycleActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.myapplication.helper.HelperMethod.replace;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResetPasswordFragment extends Fragment {


    Unbinder unbinder;

    public ResetPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);
        unbinder = ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.Reset_Password_Fragment_Btn_Change)
    public void onViewClicked()
    {
        NewPassWordFragment newPassWordFragment = new NewPassWordFragment();
        replace(newPassWordFragment,getActivity().getSupportFragmentManager(),R.id.User_Cycle_Activity_Fl_Container);

    }
}
