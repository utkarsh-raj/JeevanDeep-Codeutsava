package com.codeutsava.jeevandeep.home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.codeutsava.jeevandeep.R;

import butterknife.BindView;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener   {

    @BindView(R.id.homeToolbar)
    Toolbar homeToolbar;
    @BindView(R.id.navigationDrawer)
    NavigationView navigationDrawer;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setSupportActionBar(homeToolbar);
        setFragment(new HomeFragment(), "Home");


//        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, homeToolbar, R.string.open, R.string.close);
//        drawerLayout.addDrawerListener(drawerToggle);
//        drawerToggle.getDrawerArrowDrawable();
//        drawerToggle.syncState();

//        navigationDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                int id = menuItem.getItemId();
//
//                switch (id) {
//                    case R.id.navHome:
//                        Intent home = new Intent(HomeActivity.this, HomeActivity.class);
//                        startActivity(home);
//                        finish();
//                        break;
//                    case R.id.navAboutUs:
//
//                        break;
//                    case R.id.navContactUs:
//
//                        break;
//                    case R.id.navLogin:
////
//                        break;
//                    case R.id.navLogout:
//
//                        break;
//
//                }
//                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
//                    drawerLayout.closeDrawer(Gravity.LEFT);
//                }
//                return false;
//           }
//        });
//

//        FirebaseInstanceId.getInstance().getInstanceId()
//                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
//                        if (!task.isSuccessful()) {
//                            Log.w("getInstanceId failed", task.getException());
//                            return;
//                        }
//                        // Get new Instance ID token
//                        String token = task.getResult().getToken();
//                        Log.d("Amrita", token);
//                    }
//                });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setFragment(Fragment fragment, String title) {

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.homeContainer, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
//            getSupportActionBar().setTitle(title);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id) {
            case R.id.navHome:
                Intent home = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(home);
                finish();
                break;
            case R.id.navAboutUs:

                break;
            case R.id.navContactUs:

                break;
            case R.id.navLogin:
//
                break;
            case R.id.navLogout:

                break;

        }
        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        }
        return false;

    }
}
