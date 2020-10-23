package com.myapplication.ui.fragment.splachCycle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.myapplication.R;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import static com.myapplication.helper.HelperMethod.replace;
import static com.myapplication.helper.HelperMethod.setAppLanguage;


public class ChooseLanguageFragment extends Fragment
{
    Unbinder unbinder;

    public ChooseLanguageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose_language, container, false);
        unbinder = ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.Choose_Language_Fragment_Tv_Arabic, R.id.Choose_Language_Fragment_Tv_English})
    public void onViewClicked(View view)
    {
        SliderFragment sliderFragment = new SliderFragment();

        switch (view.getId()) {
            case R.id.Choose_Language_Fragment_Tv_Arabic:
                setAppLanguage(getContext(), "ar");
                replace(sliderFragment, getActivity().getSupportFragmentManager(), R.id.Splash_Cycle_Activity_Fl_Container);
                break;
            case R.id.Choose_Language_Fragment_Tv_English:
                setAppLanguage(getContext(), "en");
                replace(sliderFragment, getActivity().getSupportFragmentManager(), R.id.Splash_Cycle_Activity_Fl_Container);
                break;
        }
    }

}
