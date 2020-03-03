package com.mealsharing.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class FindMealOptionsActivity extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_meal_options);
        setTitle("Find a Meal");
    }
    public void onFindMealButton(View view) {
        Intent intent = new Intent(this, FindMealRecyclerViewActivity.class);
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }
    public void onViewRequest(View view) {
        Intent intent = new Intent(this, FindMealViewMyRequestRecycleViewActiviy.class);
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);


    }

    public void onMapButton(View view){
        // Check GPS is enabled
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "Please enable location services", Toast.LENGTH_LONG).show();
            finish();
        }

        // Check location permission is granted - if it is, start
        // the service, otherwise request the permission
        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            System.out.println("PERMISSION GRANTED");
//            Toast.makeText(this, "PERMISSION SERVICE ACCEPTED", Toast.LENGTH_SHORT);
//            startTrackerService();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST);
        }
//        todo multithreading for this?
//        startTrackerService();
        PhotoDecodeRunnable test= new PhotoDecodeRunnable();
        test.run();
        startActivity(new Intent(this, LocationSharingMapsActivity.class));



    }
    class PhotoDecodeRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("RUNNING");
            startTrackerService();
        }
    }

        private void startTrackerService() {
        startService(new Intent(this, TrackerService.class));
//        finish();
    }

}
