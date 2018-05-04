package com.example.sushil.actionbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.viewpagerindicator.CirclePageIndicator;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  Button b1;
    private Fragment fr;
    private FragmentManager frm;
    ImageSlider imageSlider;
    ViewPager viewPager;
    Handler handler;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.exam,R.drawable.plate,R.drawable.splash,R.drawable.java};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        b1=(Button)findViewById(R.id.button2);
        b1.setOnClickListener(this);
        actionBar.setIcon(R.drawable.exam);
       // adapter=new ImageSlider(this, ImagesArray);
        //viewPager=(ViewPager)findViewById(R.id.imageslider);
        //viewPager.setAdapter(adapter);
        init();
    }
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getApplicationContext(),Second.class);
        startActivity(intent);
    }

    private void init() {
        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);
        viewPager = (ViewPager) findViewById(R.id.imageslider);
        imageSlider=new ImageSlider(this,ImagesArray);
       // viewPager.setAdapter(new ImageSlider(MainActivity.this,ImagesArray));
        viewPager.setAdapter(imageSlider);
        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);

        indicator.setViewPager(viewPager);

        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(5 * density);
        NUM_PAGES =IMAGES.length;
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 30, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                fr=new Fragment1();
                frm=getSupportFragmentManager();
                frm.beginTransaction()
                        .replace(R.id.frame1, fr)
                        .commit();
                return true;
        }
        switch (item.getItemId()){
            case  R.id.action_delete:
                Intent intent=new Intent(MainActivity.this,Second.class);
                startActivity(intent);
                return true;

        }
        switch (item.getItemId()){
            case  R.id.setting:
                fr=new Fragment2();
                frm=getSupportFragmentManager();
                frm.beginTransaction()
                        .replace(R.id.frame1, fr)
                        .commit();
                return true;
        }

        switch (item.getItemId()){
            case  R.id.scroll:
                Intent intent=new Intent(MainActivity.this,TabbedView.class);
                startActivity(intent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }



}
