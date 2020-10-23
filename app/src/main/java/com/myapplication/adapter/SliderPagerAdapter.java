package com.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.myapplication.R;

import butterknife.BindView;

public class SliderPagerAdapter extends PagerAdapter {

    private Context context;
    private String[] abouts;
    private Button sliderFragmentBtnSkip;

    public SliderPagerAdapter(Context context, String[] abouts, Button sliderFragmentBtnSkip) {
        this.context = context;
        this.abouts = abouts;
        this.sliderFragmentBtnSkip = sliderFragmentBtnSkip;
    }

    @Override
    public int getCount() {
        return abouts.length;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.item1_slider, null);

        TextView Item1SliderTvContentText = slideLayout.findViewById(R.id.Item1_Slider_Tv_Content_Text);
        Item1SliderTvContentText.setText(abouts[position]);
        container.addView(slideLayout);

        return slideLayout;


    }



    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);

    }
}
