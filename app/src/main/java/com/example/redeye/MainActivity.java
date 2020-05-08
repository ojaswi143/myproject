package com.example.redeye;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
 FragmentPagerAdapter adapterviewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewpager=findViewById(R.id.viewpager);
        adapterviewpager =new MyPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapterviewpager);
        viewpager.setCurrentItem(1);


    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch(position)
            {
                case 0: return  Chatfragment.newInstance();

                case 1: return  Camerafragment.newInstance();

                case 3:return  Storyfragment.newInstance();

            }
            return null;
        }

        @Override
        public int getCount() {

            return 3;
        }
    }
}
