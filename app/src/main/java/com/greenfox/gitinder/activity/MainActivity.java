package com.greenfox.gitinder.activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.adapter.SectionsPageAdapter;
import com.greenfox.gitinder.api.service.GitinderAPI;
import com.greenfox.gitinder.fragment.main.MatchesFragment;
import com.greenfox.gitinder.fragment.main.SettingsFragment;
import com.greenfox.gitinder.fragment.main.SwipingFragment;
import com.greenfox.gitinder.model.Match;
import com.greenfox.gitinder.model.Matches;
import com.greenfox.gitinder.model.NonSwipeableViewPager;
import com.greenfox.gitinder.model.factory.MatchFactory;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private NonSwipeableViewPager mViewPager;
    Context ctx = this;

    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        Match match = MatchFactory.createNewMatch();
        getBitmap(match);
        if (!sharedPreferences.contains(Constants.GITINDER_TOKEN)){
            Log.d(TAG, "Token is missing!");
            toLogin();
        } else {
            Log.d(TAG, "Token is present.");
        }

        Log.d(TAG, "onCreate: Starting.");

        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.gitinder_icon);
    }

    public void setupViewPager(NonSwipeableViewPager viewPager){
        SectionsPageAdapter sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        sectionsPageAdapter.addFragment(new SwipingFragment(), getString(R.string.tab_title_swiping));
        sectionsPageAdapter.addFragment(new MatchesFragment(), getString(R.string.tab_title_matches));
        sectionsPageAdapter.addFragment(new SettingsFragment(), getString(R.string.tab_title_settings));
        viewPager.setAdapter(sectionsPageAdapter);
    }

    public void toLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Matches";
            String description = "Matches notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(Constants.NOTIFICATION_CHANNEL, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void pushNotificationMatches(final Match match, Bitmap bitmap) {
        Log.d(TAG, "pushNotificationMatches: Steped into the push method");
        Intent intent = new Intent(this, MatchesFragment.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(ctx, 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, Constants.NOTIFICATION_CHANNEL)
                .setSmallIcon(R.mipmap.gitinder_icon)
                .setContentTitle(match.getUsername())
                .setContentText(getText(R.string.notification_new_match))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setLargeIcon(bitmap)
                .setGroup(Constants.NEW_MATCH_GROUP)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        Log.d(TAG, "pushNotificationMatches: Notification created");
        NotificationManagerCompat manager = NotificationManagerCompat.from(ctx);
        manager.notify(1, mBuilder.build());
        Log.d(TAG, "pushNotificationMatches: notification fired");
    }

    private void getBitmap(final Match match) {
        Glide.with(this).asBitmap().load(match.getAvatarUrl()).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                pushNotificationMatches(match, getCircleBitmap(resource));
            }
        });
    }

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        Log.d(TAG, "getCircleBitmap: entered");
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        bitmap.recycle();

        return output;
    }
}
