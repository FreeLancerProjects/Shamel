package com.endpoint.shamel.activities_fragments.activity_home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import com.endpoint.shamel.R;
import com.endpoint.shamel.activities_fragments.activity_home.fragments.Fragment_Favourite;
import com.endpoint.shamel.activities_fragments.activity_home.fragments.Fragment_Main;
import com.endpoint.shamel.activities_fragments.activity_home.fragments.Fragment_More;
import com.endpoint.shamel.activities_fragments.activity_home.fragments.Fragment_Notifications;
import com.endpoint.shamel.activities_fragments.activity_home.fragments.Fragment_Profile;
import com.endpoint.shamel.databinding.ActivityHomeBinding;
import com.endpoint.shamel.language.Language;
import com.endpoint.shamel.models.UserModel;
import com.endpoint.shamel.preferences.Preferences;
import com.endpoint.shamel.share.Common;

import java.util.Locale;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    private FragmentManager fragmentManager;
    private Fragment_Main fragment_main;
    private Fragment_Favourite fragment_favourite;
    private Fragment_More fragment_more;
    private Fragment_Profile fragment_profile;
    private Fragment_Notifications fragment_notifications;
    private AHBottomNavigation ahBottomNav;
    private LinearLayoutManager manager;
    private TextView tv_title, tvname, tvphone;
    private DrawerLayout drawer;
    private Preferences preferences;
    private UserModel userModel;


    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", Locale.getDefault().getLanguage())));}




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        initView();


        if (savedInstanceState == null) {

            displayFragmentMain();
        }


    }

    @SuppressLint("RestrictedApi")
    private void initView() {
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);
        Paper.init(this);


        setUpBottomNavigation();

    }

    public void Logout() {
//        userModel = null;
//        preferences.create_update_userdata(this, userModel);
//        preferences.create_update_session(this, Tags.session_logout);
//        Intent intent = new Intent(HomeActivity.this, SignInActivity.class);
//        startActivity(intent);
//        finish();
    }



    private void setUpBottomNavigation() {

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(getResources().getString(R.string.home), R.drawable.ic_add);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(getResources().getString(R.string.favourite), R.drawable.ic_add);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(getResources().getString(R.string.profile), R.drawable.ic_add);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(getResources().getString(R.string.notifications), R.drawable.ic_add);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(getResources().getString(R.string.more), R.drawable.ic_add);

        ahBottomNav.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        ahBottomNav.setDefaultBackgroundColor(ContextCompat.getColor(this, R.color.white));
        ahBottomNav.setTitleTextSizeInSp(14, 12);
        ahBottomNav.setForceTint(true);
        ahBottomNav.setAccentColor(ContextCompat.getColor(this, R.color.colorAccent));
        ahBottomNav.setInactiveColor(ContextCompat.getColor(this, R.color.gray7));

        ahBottomNav.addItem(item1);
        ahBottomNav.addItem(item2);
        ahBottomNav.addItem(item3);
        ahBottomNav.addItem(item4);
        ahBottomNav.addItem(item5);

        updateBottomNavigationPosition(0);

        ahBottomNav.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case 0:
                        displayFragmentMain();
                        break;
                    case 1:
                        displayFragmentFavourite();

                        break;
                    case 2:
                        displayFragmentProfile();


                        break;
                    case 3:
                        displayFragmentNotifications();

                        break;
                    case 4:
                        displayFragmentMore();
                        break;
                }
                return false;
            }
        });


    }

    private void updateBottomNavigationPosition(int pos) {

        ahBottomNav.setCurrentItem(pos, false);

    }

    public void displayFragmentMain() {
        try {
            if (fragment_main == null) {
                fragment_main = Fragment_Main.newInstance();
            }

            if (fragment_notifications != null && fragment_notifications.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_notifications).commit();
            }
            if (fragment_favourite != null && fragment_favourite.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_favourite).commit();
            }
            if (fragment_more != null && fragment_more.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_more).commit();
            }
            if (fragment_profile != null && fragment_profile.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_profile).commit();
            }
            if (fragment_main.isAdded()) {
                fragmentManager.beginTransaction().show(fragment_main).commit();

            } else {
                fragmentManager.beginTransaction().add(R.id.fragment_app_container, fragment_main, "fragment_main").addToBackStack("fragment_main").commit();

            }
            updateBottomNavigationPosition(0);
        } catch (Exception e) {
        }

    }

    private void displayFragmentNotifications() {
        try {
            if (fragment_notifications == null) {
                fragment_notifications = Fragment_Notifications.newInstance();
            }
            if (fragment_main != null && fragment_main.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_main).commit();
            }
            if (fragment_favourite != null && fragment_favourite.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_favourite).commit();
            }
            if (fragment_more != null && fragment_more.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_more).commit();
            }
            if (fragment_profile != null && fragment_profile.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_profile).commit();
            }
            if (fragment_notifications.isAdded()) {
                fragmentManager.beginTransaction().show(fragment_notifications).commit();

            } else {
                fragmentManager.beginTransaction().add(R.id.fragment_app_container, fragment_notifications, "fragment_notifications").addToBackStack("fragment_notifications").commit();

            }
            updateBottomNavigationPosition(1);
        } catch (Exception e) {
        }

    }

    private void displayFragmentFavourite() {
        try {
            if (fragment_favourite == null) {
                fragment_favourite = Fragment_Favourite.newInstance();
            }
            if (fragment_main != null && fragment_main.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_main).commit();
            }
            if (fragment_notifications != null && fragment_notifications.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_notifications).commit();
            }
            if (fragment_more != null && fragment_more.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_more).commit();
            }
            if (fragment_profile != null && fragment_profile.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_profile).commit();
            }
            if (fragment_favourite.isAdded()) {
                fragmentManager.beginTransaction().show(fragment_favourite).commit();

            } else {
                fragmentManager.beginTransaction().add(R.id.fragment_app_container, fragment_favourite, "fragment_favourite").addToBackStack("fragment_favourite").commit();

            }
            updateBottomNavigationPosition(2);
        } catch (Exception e) {
        }

    }

    private void displayFragmentProfile() {
        try {
            if (fragment_profile == null) {
                fragment_profile = Fragment_Profile.newInstance();
            }
            if (fragment_main != null && fragment_main.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_main).commit();
            }
            if (fragment_favourite != null && fragment_favourite.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_favourite).commit();
            }
            if (fragment_notifications != null && fragment_notifications.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_notifications).commit();
            }
            if (fragment_more != null && fragment_more.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_more).commit();
            }
            if (fragment_profile.isAdded()) {
                fragmentManager.beginTransaction().show(fragment_profile).commit();

            } else {
                fragmentManager.beginTransaction().add(R.id.fragment_app_container, fragment_profile, "fragment_profile").addToBackStack("fragment_profile").commit();

            }
            updateBottomNavigationPosition(3);
        } catch (Exception e) {
        }
    }

    private void displayFragmentMore() {
        try {
            if (fragment_more == null) {
                fragment_more = Fragment_More.newInstance();
            }
            if (fragment_main != null && fragment_main.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_main).commit();
            }
            if (fragment_favourite != null && fragment_favourite.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_favourite).commit();
            }
            if (fragment_notifications != null && fragment_notifications.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_notifications).commit();
            }
            if (fragment_profile != null && fragment_profile.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_profile).commit();
            }
            if (fragment_more.isAdded()) {
                fragmentManager.beginTransaction().show(fragment_more).commit();

            } else {
                fragmentManager.beginTransaction().add(R.id.fragment_app_container, fragment_more, "fragment_more").addToBackStack("fragment_more").commit();

            }
            updateBottomNavigationPosition(4);
        } catch (Exception e) {
        }
    }



    public void RefreshActivity(String lang) {
        Paper.book().write("lang", lang);
        Language.setNewLocale(this, lang);
        new Handler()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                }, 1050);


    }


    @SuppressLint("RestrictedApi")
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (fragment_main != null && fragment_main.isAdded() && fragment_main.isVisible()) {
                finish();
            } else {
                displayFragmentMain();
            }

        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}
