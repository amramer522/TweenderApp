package com.myapplication.ui.fragment.homeCycle;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myapplication.R;
import com.myapplication.data.model.User;
import com.myapplication.helper.DBConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    @BindView(R.id.Profile_Fragment_Civ_User_Img)
    CircleImageView ProfileFragmentCivUserImg;
    @BindView(R.id.Profile_Fragment_Civ_User_Name)
    TextView ProfileFragmentCivUserName;
    @BindView(R.id.Profile_Fragment_Tv_Email_Address)
    TextView ProfileFragmentTvEmailAddress;
    @BindView(R.id.Profile_Fragment_Tv_Phone_Number)
    TextView ProfileFragmentTvPhoneNumber;
    @BindView(R.id.Profile_Fragment_Tv_Gender)
    TextView ProfileFragmentTvGender;
    @BindView(R.id.Profile_Fragment_Tv_About)
    TextView ProfileFragmentTvAbout;
    Unbinder unbinder;
    private DBConnection db;
    private SharedPreferences preferences;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, view);
        db = new DBConnection(getContext());
        preferences = getContext().getSharedPreferences("userData", Context.MODE_PRIVATE);
        String userId = preferences.getString("user_ID", "");
        User user = db.getUserObject(getContext(), userId);
//        ProfileFragmentCivUserImg.setImageBitmap(user.getBitmap_img());
        ProfileFragmentCivUserName.setText("Name : " + user.getUname());
        ProfileFragmentTvEmailAddress.setText("Email Address : " + user.getUemail());
        ProfileFragmentTvPhoneNumber.setText("Phone Number : " + user.getUphone());
        ProfileFragmentTvGender.setText("Gender : " + user.getUgender());
        ProfileFragmentTvAbout.setText("Status : " + user.getUstatus());


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
