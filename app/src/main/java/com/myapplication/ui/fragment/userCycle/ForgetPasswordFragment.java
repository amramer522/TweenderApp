package com.myapplication.ui.fragment.userCycle;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.R;
import com.myapplication.helper.HelperMethod;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.myapplication.helper.HelperMethod.replace;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPasswordFragment extends Fragment {


    @BindView(R.id.Forget_Password_Fragment_Til_Email_Address)
    TextInputLayout ForgetPasswordFragmentTilEmailAddress;

    Unbinder unbinder;

    public ForgetPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget_password, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.Forget_Password_Fragment_Btn_Send_Code, R.id.Forget_Password_Fragment_Tv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Forget_Password_Fragment_Btn_Send_Code:
                ResetPasswordFragment resetPasswordFragment = new ResetPasswordFragment();
                replace(resetPasswordFragment, getActivity().getSupportFragmentManager(), R.id.User_Cycle_Activity_Fl_Container);
                break;
            case R.id.Forget_Password_Fragment_Tv_back:
                LoginFragment loginFragment = new LoginFragment();
                replace(loginFragment, getActivity().getSupportFragmentManager(), R.id.User_Cycle_Activity_Fl_Container);
                break;
        }
    }
}
