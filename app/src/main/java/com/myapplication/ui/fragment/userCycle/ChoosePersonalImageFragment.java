package com.myapplication.ui.fragment.userCycle;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.myapplication.R;
import com.myapplication.helper.DBConnection;
import com.myapplication.helper.HelperMethod;

import java.io.IOException;
import java.util.ResourceBundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;
import static com.myapplication.helper.HelperMethod.replace;
import static com.myapplication.helper.HelperMethod.showToastMsg;

public class ChoosePersonalImageFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.choose_personal_image_fragment_Civ_Personal_Img)
    CircleImageView choosePersonalImageFragmentCivPersonalImg;
    @BindView(R.id.choose_personal_image_fragment_Et_About_Text)
    EditText choosePersonalImageFragmentEtAboutText;
    private Intent intent;
    private int PICK_IMAGE_REQUEST = 1;
    private DBConnection db;
    private Uri uri;
    private String email;
    private SharedPreferences preferences;


    public ChoosePersonalImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose_personal_image, container, false);
        unbinder = ButterKnife.bind(this, view);
        db = new DBConnection(getContext());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.choose_personal_image_fragment_Civ_Select_Img, R.id.choose_personal_image_fragment_Btn_Save, R.id.choose_personal_image_fragment_Btn_Skip})
    public void onViewClicked(View view) {
        TwitterConnectionFragment twitterConnectionFragment = new TwitterConnectionFragment();
        preferences = getContext().getSharedPreferences("userData", Context.MODE_PRIVATE);
        String userId = preferences.getString("user_ID", "");
        switch (view.getId()) {
            case R.id.choose_personal_image_fragment_Civ_Select_Img:
                selectImg();
                break;
            case R.id.choose_personal_image_fragment_Btn_Save:
                String status = HelperMethod.getTextFromEt(choosePersonalImageFragmentEtAboutText);
                db.insertImageWithStatus(getContext(), userId, uri, status);
                replace(twitterConnectionFragment, getActivity().getSupportFragmentManager(), R.id.User_Cycle_Activity_Fl_Container);
                break;
            case R.id.choose_personal_image_fragment_Btn_Skip:
                replace(twitterConnectionFragment, getActivity().getSupportFragmentManager(), R.id.User_Cycle_Activity_Fl_Container);
                break;
        }
    }

    private void selectImg() {
        intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super method removed
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST) {
                uri = data.getData();
                Bitmap bitmapImage = null;
                try {
                    bitmapImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                choosePersonalImageFragmentCivPersonalImg.setImageBitmap(bitmapImage);

            }
        }

    }

    public void setEmailFromRegister(String email) {
        this.email = email;
    }
}
