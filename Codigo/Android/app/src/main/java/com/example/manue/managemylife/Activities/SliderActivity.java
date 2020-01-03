package com.example.manue.managemylife.Activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.manue.managemylife.Adapters.SliderAdapter;
import com.example.manue.managemylife.R;
import android.os.Bundle;

public class SliderActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SliderAdapter sliderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        viewPager = (ViewPager) findViewById(R.id.viewPagerSlider);
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

    }
}
