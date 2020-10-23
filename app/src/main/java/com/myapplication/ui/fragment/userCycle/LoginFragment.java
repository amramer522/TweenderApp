package com.myapplication.ui.fragment.userCycle;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.myapplication.R;
import com.myapplication.data.model.User;
import com.myapplication.helper.DBConnection;
import com.myapplication.helper.HelperMethod;
import com.myapplication.ui.activity.HomeCycleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.myapplication.helper.HelperMethod.getTextFromTil;
import static com.myapplication.helper.HelperMethod.replace;
import static com.myapplication.helper.HelperMethod.setTextToTil;
import static com.myapplication.helper.HelperMethod.setTilEmpty;
import static com.myapplication.helper.HelperMethod.showToastMsg;

public class LoginFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.Login_Fragment_Til_Password)
    TextInputLayout LoginFragmentTilPassword;
    @BindView(R.id.Login_Fragment_Cb_Remember_Me)
    CheckBox LoginFragmentCbRememberMe;
    @BindView(R.id.Login_Fragment_Til_Name)
    TextInputLayout LoginFragmentTilName;
    private SharedPreferences preferences;
    private DBConnection db;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);


        db = new DBConnection(getContext());
        preferences = this.getActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
        String uname = preferences.getString("name", "");
        String password = preferences.getString("password", "");

        setTextToTil(LoginFragmentTilName, uname);
        setTextToTil(LoginFragmentTilPassword, password);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.Login_Fragment_Btn_Login, R.id.Login_Fragment_Tv_Forget_Password, R.id.Login_Fragment_Tv_Register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Login_Fragment_Btn_Login:
                login();
                break;
            case R.id.Login_Fragment_Tv_Forget_Password:
                ForgetPasswordFragment forgetPasswordFragment = new ForgetPasswordFragment();
                replace(forgetPasswordFragment, getActivity().getSupportFragmentManager(), R.id.User_Cycle_Activity_Fl_Container);
                break;
            case R.id.Login_Fragment_Tv_Register:
                RegisterFragment registerFragment = new RegisterFragment();
                replace(registerFragment, getActivity().getSupportFragmentManager(), R.id.User_Cycle_Activity_Fl_Container);
                break;
        }
    }

    public void login() {
        String name = getTextFromTil(LoginFragmentTilName);
        String pass = getTextFromTil(LoginFragmentTilPassword);
        if (db.checkUser(getContext(), name, pass)) {
            SharedPreferences.Editor editor = preferences.edit();
            if (LoginFragmentCbRememberMe.isChecked()) {
                editor.putString("name", name);
                editor.putString("password", pass);
            }
            String user_id = db.getUserId(getContext(), name);
            editor.putBoolean("status", true);
            editor.putString("user_ID", user_id);
            editor.commit();

            getActivity().startActivity(new Intent(getActivity(), HomeCycleActivity.class));
            getActivity().finish();
        } else if (db.checkNameExist(getContext(), name)) {
            showToastMsg(getContext(), "please check your password");
            setTilEmpty(LoginFragmentTilPassword);
        } else {
            showToastMsg(getContext(), "check your data");
            setTilEmpty(LoginFragmentTilName);
            setTilEmpty(LoginFragmentTilPassword);
        }
    }
}
