package com.travel.daily.traveldaily;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.travel.daily.traveldaily.account.AccountManager;
import com.travel.daily.traveldaily.account.LoginActivity;
import com.travel.daily.traveldaily.bill.BillListFragment;
import com.travel.daily.traveldaily.database.dao.AccountBean;
import com.travel.daily.traveldaily.debug.DebugMainActivity;
import com.travel.daily.traveldaily.delicacy.DelicacyListFragment;
import com.travel.daily.traveldaily.hotel.HotelListFragment;
import com.travel.daily.traveldaily.scenery.SceneryListFragment;
import com.travel.daily.traveldaily.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TabLayout mTabLayout;
    ViewPager mViewPager;
    AccountBean account;
    ImageView img;
    TextView name;
    TextView detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        img = (ImageView) view.findViewById(R.id.imageView);
        name = (TextView) view.findViewById(R.id.name);
        detail = (TextView) view.findViewById(R.id.textView);

        account = AccountManager.getInstance(this).getBean();

        setUpViewPager();
    }

    @Override
    protected void onResume() {
        super.onResume();
        account = AccountManager.getInstance(this).getBean();
        updateAccount();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                if (mViewPager != null) {
                    ((FragmentAdapter)mViewPager.getAdapter()).refresh();
                }
                break;
            case R.id.action_search:
                startActivity(new Intent(this, SearchActivity.class));
            default:
                break;
        }
        return true;
    }

    private void updateAccount() {
        account = AccountManager.getInstance(this).getBean();
        if (account == null) {
            img.setImageResource(R.drawable.guest);
            name.setText("没有登录");
            detail.setText("点击头像登录");
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            });
            return;
        }
        img.setImageBitmap(ToolUtils.getBitmapFromUrl(this, account.getImgUrl()));
        name.setText(account.getName());
        detail.setText(account.getDetail());
        img.setOnClickListener(null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage) {
            startActivity(new Intent(this, DebugMainActivity.class));
        } else if (id == R.id.logout) {
            AccountManager.getInstance(this).clear();
            updateAccount();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setUpViewPager() {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        List<String> titles = new ArrayList<>();
        titles.add("美景名胜");
        titles.add("娱乐酒店");
        titles.add("小吃美食");
        titles.add("旅程记录");
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(2)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(3)));
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new SceneryListFragment());
        fragments.add(new HotelListFragment());
        fragments.add(new DelicacyListFragment());
        fragments.add(new BillListFragment());
        FragmentAdapter adapter =
                new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);
    }

    public class FragmentAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> mFragments;
        private List<String> mTitles;

        public FragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
            super(fm);
            mFragments = fragments;
            mTitles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

        public void refresh() {
            for (Fragment fragment : mFragments) {
                ((BaseListFragment)fragment).refresh();
            }
        }
    }
}
