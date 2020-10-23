package com.myapplication.ui.fragment.userCycle;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.myapplication.R;
import com.myapplication.data.model.User;
import com.myapplication.helper.DBConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.myapplication.helper.HelperMethod.getTextFromTil;
import static com.myapplication.helper.HelperMethod.isEmailValid;
import static com.myapplication.helper.HelperMethod.replace;
import static com.myapplication.helper.HelperMethod.setTilEmpty;
import static com.myapplication.helper.HelperMethod.showToastMsg;
import static com.myapplication.helper.HelperMethod.tilsVaild;


public class RegisterFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.Register_Fragment_Til_Name)
    TextInputLayout RegisterFragmentTilName;
    @BindView(R.id.Register_Fragment_Til_Email_Address)
    TextInputLayout RegisterFragmentTilEmailAddress;
    @BindView(R.id.Register_Fragment_Til_Phone_Number)
    TextInputLayout RegisterFragmentTilPhoneNumber;
    @BindView(R.id.Register_Fragment_Til_Password)
    TextInputLayout RegisterFragmentTilPassword;
    @BindView(R.id.Register_Fragment_Til_Confirm_Password)
    TextInputLayout RegisterFragmentTilConfirmPassword;
    @BindView(R.id.Register_Fragment_Rg_Gender)
    RadioGroup RegisterFragmentRgGender;
    RadioButton radioButton;
    private DBConnection db;
    private SharedPreferences preferences;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        unbinder = ButterKnife.bind(this, view);
        db = new DBConnection(getContext());

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.Register_Fragment_Btn_Register, R.id.Register_Fragment_Tv_Back_To_Login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Register_Fragment_Btn_Register:
                String name = getTextFromTil(RegisterFragmentTilName);
                String phone = getTextFromTil(RegisterFragmentTilPhoneNumber);
                String email = getTextFromTil(RegisterFragmentTilEmailAddress);
                String password = getTextFromTil(RegisterFragmentTilPassword);
                String confirm_password = getTextFromTil(RegisterFragmentTilConfirmPassword);
                String gender = checkedButton();

                if (tilsVaild(name, email, phone, gender, password, confirm_password))
                {
                    if (TextUtils.equals(password, confirm_password))
                    {
                        if (db.checkEmailExist(getContext(), email))
                        {
//                            RegisterFragmentTilEmailAddress.setError("Email not available");
                            showToastMsg(getContext(),"Email not available");

                            setTilEmpty(RegisterFragmentTilEmailAddress);
                        } else if (db.checkNameExist(getContext(), name))
                        {
//                            RegisterFragmentTilName.setError("Name not available");
                            showToastMsg(getContext(),"Name not available");

                            setTilEmpty(RegisterFragmentTilName);
                        }
                        else if (password.length() <= 7)
                        {
//                            RegisterFragmentTilPassword.setError("at least 7 digits");
                            showToastMsg(getContext(),"Password must be at least 7 digits");

                        } else if (!isEmailValid(email))
                        {
//                            RegisterFragmentTilEmailAddress.setError("Invalid Format");
                            showToastMsg(getContext(),"Email Invalid Format");
                            setTilEmpty(RegisterFragmentTilEmailAddress);
                        } else
                            {
                            User user = new User(name, phone, email, password, gender);
                            db.insertUser(getContext(), user);
                                // put id in shared prefrences
                            String user_id = db.getUserId(getContext(), name);
                            preferences = getContext().getSharedPreferences("userData", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("user_ID", user_id);
                            editor.commit();
                            ChoosePersonalImageFragment choosePersonalImageFragment = new ChoosePersonalImageFragment();
                            choosePersonalImageFragment.setEmailFromRegister(email);
                            replace(choosePersonalImageFragment, getActivity().getSupportFragmentManager(), R.id.User_Cycle_Activity_Fl_Container);
                        }

                    } else {
                        showToastMsg(getContext(),"Password not matched");
//                        RegisterFragmentTilPassword.setError("Passwords not match");
//                        RegisterFragmentTilConfirmPassword.setError("Passwords not match");
                    }

                }
                else
                    {
                        showToastMsg(getContext(),"Fill All Fields");
//                    RegisterFragmentTilName.setError("Fill this field");
//                    RegisterFragmentTilEmailAddress.setError("Fill this field");
//                    RegisterFragmentTilPhoneNumber.setError("Fill this field");
//                    RegisterFragmentTilPassword.setError("Fill this field");
//                    RegisterFragmentTilConfirmPassword.setError("Fill this field");
                }
                break;
            case R.id.Register_Fragment_Tv_Back_To_Login:
                LoginFragment loginFragment = new LoginFragment();
                replace(loginFragment, getActivity().getSupportFragmentManager(), R.id.User_Cycle_Activity_Fl_Container);
                break;
        }
    }

    public String checkedButton()
    {
        int radioID = RegisterFragmentRgGender.getCheckedRadioButtonId();
        radioButton = getActivity().findViewById(radioID);
        String radioButton_Checked = radioButton.getText().toString();
        return radioButton_Checked;
    }
}
