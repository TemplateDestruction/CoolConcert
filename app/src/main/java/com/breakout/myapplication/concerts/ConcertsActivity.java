package com.breakout.myapplication.concerts;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;
import com.breakout.myapplication.R;
import com.breakout.myapplication.concerts.fragments.current.CurrentConcertsFragment;
import com.breakout.myapplication.concerts.fragments.future.FutureConcertsFragment;
import com.breakout.myapplication.concerts.fragments.PreviousConcertsFragment;

public class ConcertsActivity extends FragmentActivity {
    static final int PAGE_COUNT = 3;

    ViewPager pager;
    PagerAdapter pagerAdapter;

    PagerSlidingTabStrip strip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.concerts_layout);


        strip = findViewById(R.id.pagerTabStrip);
        strip.setBackgroundColor(getResources().getColor(R.color.linkColor));
//        strip.setDistributeEvenly(true);
//        strip.setForegroundGravity(Gravity.START);
        pager = findViewById(R.id.concert_pager);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        strip.setViewPager(pager);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return FutureConcertsFragment.getInstance();
                case 1:
                    return PreviousConcertsFragment.getInstance();
                case 2:
                    return CurrentConcertsFragment.getInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //добавить картинку
//            SpannableStringBuilder sb = new SpannableStringBuilder(" " + "Page #"+ position); // space added before text for convenience
//
//            myDrawable.setBounds(0, 0, myDrawable.getIntrinsicWidth(), myDrawable.getIntrinsicHeight());
//            ImageSpan span = new ImageSpan(myDrawable, ImageSpan.ALIGN_BASELINE);
//            sb.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//            return sb;
            switch (position) {
                case 0:
                    return "Будущие";
                case 1:
                    return "Прошедшие";
                case 2:
                    return "Текущие";
            }
            return null;
        }

    }

}
