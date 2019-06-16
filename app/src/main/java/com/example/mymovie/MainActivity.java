package com.example.mymovie;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    FragmentStateManager fragmentStateManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout content = findViewById(R.id.container_layout);
        fragmentStateManager = new FragmentStateManager(content, getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new MoviesFragment();
                    case 1:
                        return new TvShowFragment();
                    case 2:
                        return new FavoriteFragment();
                    default:
                        return null;
                }
            }
        };
        if (savedInstanceState == null) {
            fragmentStateManager.changeFragment(0);
        }

        BottomNavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setOnNavigationItemReselectedListener(reselectedListener);
        ;
        navigationView.setOnNavigationItemSelectedListener(itemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int pos = getNavPositionFromMenuItem(item);
            if (pos != -1) {
                fragmentStateManager.changeFragment(getNavPositionFromMenuItem(item));
                return true;
            }
            return false;
        }
    };

    private BottomNavigationView.OnNavigationItemReselectedListener reselectedListener
            = new BottomNavigationView.OnNavigationItemReselectedListener() {
        @Override
        public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
            int position = getNavPositionFromMenuItem(menuItem);
            if (position != -1) {
                fragmentStateManager.removeFragment(position);
                fragmentStateManager.changeFragment(position);
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    int getNavPositionFromMenuItem(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_movie:
                return 0;
            case R.id.navigation_tv_show:
                return 1;
            case R.id.navigation_favorite:
                return 2;
            default:
                return -1;
        }
    }
}
