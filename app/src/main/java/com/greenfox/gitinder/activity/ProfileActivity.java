package com.greenfox.gitinder.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.greenfox.gitinder.R;
import com.greenfox.gitinder.adapter.SectionsPageAdapter;
import com.greenfox.gitinder.fragment.profile.CodeFragment;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    private ImageView profilePic;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private SectionsPageAdapter sectionsPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        viewPager = findViewById(R.id.profile_details_layout_tab_viewpager);
        setupViewPager(viewPager);

        toolbar = (Toolbar) findViewById(R.id.profile_details_layout_name);
        toolbar.setTitle("Tom Hanks");

        profilePic = findViewById(R.id.profile_details_layout_picture);
        Picasso.get().load("https://www.randomlists.com/img/people/tom_hanks.jpg").into(profilePic);

        setSupportActionBar(toolbar);
        TabLayout tabLayout = findViewById(R.id.profile_details_layout_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setupViewPager(ViewPager viewPager){
        sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        sectionsPageAdapter.addFragment(new CodeFragment(), "1");
        sectionsPageAdapter.addFragment(new CodeFragment(), "2");
        sectionsPageAdapter.addFragment(new CodeFragment(), "3");
        sectionsPageAdapter.addFragment(new CodeFragment(), "4");
        sectionsPageAdapter.addFragment(new CodeFragment(), "5");
        viewPager.setAdapter(sectionsPageAdapter);
    }
}
