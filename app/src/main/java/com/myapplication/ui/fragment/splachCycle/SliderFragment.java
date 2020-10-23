package com.myapplication.ui.fragment.splachCycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.myapplication.R;
import com.myapplication.adapter.SliderPagerAdapter;
import com.myapplication.ui.activity.SplashCycleActivity;
import com.myapplication.ui.activity.UserCycleActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SliderFragment extends Fragment {

    Unbinder unbinder;
    String[] abouts = new String[]{"Start Using Twitter and let Tweender App clean annoying tweets fot you.", "We help you to control what you see.", "We provide a best environment for your children while browsing the tweets."};
    @BindView(R.id.Slider_Fragment_Vp_Pager)
    ViewPager SliderFragmentVpPager;
    @BindView(R.id.Slider_Fragment_Tl_Indicator)
    TabLayout SliderFragmentTlIndicator;
    @BindView(R.id.Slider_Fragment_Btn_Skip)
    Button SliderFragmentBtnSkip;

    public SliderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slider, container, false);
        unbinder = ButterKnife.bind(this, view);

        SliderPagerAdapter adapter = new SliderPagerAdapter(getActivity(), abouts, SliderFragmentBtnSkip);
        SliderFragmentVpPager.setAdapter(adapter);
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new SliderFragment.SliderTimer(), 5000, 5000);
        SliderFragmentTlIndicator.setupWithViewPager(SliderFragmentVpPager, true);


        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.Slider_Fragment_Btn_Skip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Slider_Fragment_Btn_Skip:
                startActivity(new Intent(getActivity(), UserCycleActivity.class));
                getActivity().finish();
                break;
        }
    }


    class SliderTimer extends TimerTask {

        @Override
        public void run() {
            if (isAdded()) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (SliderFragmentVpPager.getCurrentItem() < abouts.length - 1) {
                            SliderFragmentVpPager.setCurrentItem(SliderFragmentVpPager.getCurrentItem() + 1);
                        }

                    }
                });
            }
        }
    }


}
