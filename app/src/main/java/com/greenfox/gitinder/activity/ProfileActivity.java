package com.greenfox.gitinder.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.adapter.SectionsPageAdapter;
import com.greenfox.gitinder.fragment.profile.CodeFragment;
import com.greenfox.gitinder.model.NonSwipeableViewPager;
import com.greenfox.gitinder.model.Profile;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    private ImageView profilePic;
    private Toolbar toolbar;
    private NonSwipeableViewPager viewPager;
    private SectionsPageAdapter sectionsPageAdapter;
    private Profile profile;
    private Toolbar mainToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profile = (Profile) getIntent().getSerializableExtra("profile");

        viewPager = findViewById(R.id.profile_details_layout_tab_viewpager);
        setupViewPager(viewPager);

        toolbar = (Toolbar) findViewById(R.id.profile_details_layout_name);
        toolbar.setTitle(profile.getUsername());

        profilePic = findViewById(R.id.profile_details_layout_picture);
        Glide.with(this).load(profile.getAvatarUrl()).into(profilePic);

        TabLayout tabLayout = findViewById(R.id.profile_details_layout_tabs);
        tabLayout.setupWithViewPager(viewPager);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.gitinder_icon);
    }

    public void setupViewPager(ViewPager viewPager){
        sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        for (int i = 0; i < profile.getSnippets().size()  ; i++) {
            sectionsPageAdapter.addFragment(new CodeFragment(), String.valueOf(i+1));
        }
        viewPager.setAdapter(sectionsPageAdapter);
    }
}
