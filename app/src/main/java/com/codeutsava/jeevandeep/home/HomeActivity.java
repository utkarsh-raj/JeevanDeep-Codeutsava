package com.codeutsava.jeevandeep.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
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
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import butterknife.BindView;


public class HomeActivity extends AppCompatActivity implements LocationListener {

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


//        LocationManager locationManager = (LocationManager)
//                getSystemService(Context.LOCATION_SERVICE);

//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        LocationManager locationManager = (LocationManager)
                getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(bestProvider);
        try {
            Log.d("Amrita", String.valueOf(location.getLatitude()));
            Log.d("Amrita", String.valueOf(location.getLongitude()));
        } catch (NullPointerException e) {
            Log.d("Amrita", "No location");
        }


//        Dexter.withActivity(this)
//                .withPermissions(
//                        Manifest.permission.ACCESS_FINE_LOCATION
//                ).withListener(new MultiplePermissionsListener() {
//            @SuppressLint("MissingPermission")
//            @Override
//            public void onPermissionsChecked(MultiplePermissionsReport report) {
//                if (report.areAllPermissionsGranted()) {
//                    try {
//
//
//
////                        Log.d("Amrtiaa===", String.valueOf(locationListener.onLocationChanged(locationManager);));
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (report.isAnyPermissionPermanentlyDenied()) {
//                    showSettingsDialog();
//                }
//            }
//
//            @Override
//            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
//                token.continuePermissionRequest();
//            }
//        }).check();





//        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, homeToolbar, R.string.open, R.string.close);
//        drawerLayout.addDrawerListener(drawerToggle);
//        drawerToggle.getDrawerArrowDrawable();
//        drawerToggle.syncState();
//        homeToolbar.setNavigationIcon(R.drawable.back_button);
//
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

//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        int id = menuItem.getItemId();
//
//        switch (id) {
//            case R.id.navHome:
//                Intent home = new Intent(HomeActivity.this, HomeActivity.class);
//                startActivity(home);
//                finish();
//                break;
//            case R.id.navAboutUs:
//
//                break;
//            case R.id.navContactUs:
//
//                break;
//            case R.id.navLogin:
////
//                break;
//            case R.id.navLogout:
//
//                break;
//
//        }
//        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
//            drawerLayout.closeDrawer(Gravity.LEFT);
//        }
//        return false;
//
//    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("Amritaaaaaaaaa", String.valueOf(location.getLatitude()));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
