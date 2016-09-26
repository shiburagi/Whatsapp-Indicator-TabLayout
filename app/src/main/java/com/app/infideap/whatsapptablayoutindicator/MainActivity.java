package com.app.infideap.whatsapptablayoutindicator;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.app.infideap.indicatortablayout.IndicatorTabLayout;
import com.app.infideap.whatsapptablayoutindicator.list.ItemFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private IndicatorTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initTabLayout();
        initViewPager();

        // select 'Chat' tab.
        tabLayout.getTabAt(1).select();
    }


    /**
     * Initialize viewPager.
     */
    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = ItemFragment.newInstance(1);
                return fragment;
            }

            @Override
            public int getCount() {
                return tabLayout.getTabCount();
            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    /**
     * Initialize tabLayout.
     * create and add tab.
     */
    private void initTabLayout() {
        tabLayout = (IndicatorTabLayout) findViewById(R.id.tabLayout);

        TabLayout.Tab[] tabs = {
                tabLayout.newTab().setText(R.string.calls),
                tabLayout.newTab().setText(R.string.chats),
                tabLayout.newTab().setText(R.string.contacts),
        };

        for (TabLayout.Tab tab : tabs) {
            tabLayout.addTab(tab);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getSupportActionBar().setTitle(tab.getText());
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                getSupportActionBar().setTitle(tab.getText());
            }
        });

        tabLayout.getIndicatorTabAt(1).setIndicatorText("200");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
