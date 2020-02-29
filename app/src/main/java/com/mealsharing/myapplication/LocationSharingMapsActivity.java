package com.mealsharing.myapplication;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class LocationSharingMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String TAG = LocationSharingMapsActivity.class.getSimpleName();
    private HashMap<String, Marker> mMarkers = new HashMap<>();

//    market locations
    private Marker CC;
    private Marker POD;
    private Marker GoatHead;
    //        CC 42.2748, -71.8084
//        POD 42.2735, -71.8106
//        Goat Head 42.2734, -71.8054
    private static final LatLng CCLocation = new LatLng(42.2748, -71.8084);
    private static final LatLng PODLocation = new LatLng(42.2735, -71.8106);
    private static final LatLng GoatHeadLocation = new LatLng(42.2734, -71.8054);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_sharing_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        mMap.setMaxZoomPreference(16);

        // Add some markers to the map, and add a data object to each marker.
//        CC = mMap.addMarker(new MarkerOptions()
//                .position(CCLocation)
//                .title("Perth"));
//        CC.setTag(0);
//
//        POD = mMap.addMarker(new MarkerOptions()
//                .position(PODLocation)
//                .title("Sydney"));
//        POD.setTag(0);
//
//        GoatHead = mMap.addMarker(new MarkerOptions()
//                .position(GoatHeadLocation)
//                .title("Brisbane"));
//        GoatHead.setTag(0);

        // Set a listener for marker click.
//        mMap.setOnMarkerClickListener(this);

        subscribeToUpdates();
    }

    private void setLocationsMarker(){


    }

    private void subscribeToUpdates() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(getString(R.string.userLocationFirebasse));
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                setMarker(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                setMarker(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void setMarker(DataSnapshot dataSnapshot) {
        // When a location update is received, put or update
        // its value in mMarkers, which contains all the markers
        // for locations received, so that we can build the
        // boundaries required to show them all on the map at once
        String key = dataSnapshot.getKey();
        HashMap<String, Object> value = (HashMap<String, Object>) dataSnapshot.getValue();
        double lat = Double.parseDouble(value.get("latitude").toString());
        double lng = Double.parseDouble(value.get("longitude").toString());
        LatLng location = new LatLng(lat, lng);
        if (!mMarkers.containsKey(key)) {
            mMarkers.put(key, mMap.addMarker(new MarkerOptions().title(key).position(location)));
        } else {
            mMarkers.get(key).setPosition(location);
        }
//        todo change marker image 
        CC = mMap.addMarker(new MarkerOptions()
                .position(CCLocation)
                .title("Perth"));
        CC.setTag(0);

        POD = mMap.addMarker(new MarkerOptions()
                .position(PODLocation)
                .title("Sydney"));
        POD.setTag(0);

        GoatHead = mMap.addMarker(new MarkerOptions()
                .position(GoatHeadLocation)
                .title("Brisbane"));
        GoatHead.setTag(0);
        mMarkers.put("CC", CC);
        mMarkers.put("POD", POD);
        mMarkers.put("GoatHead", GoatHead);
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : mMarkers.values()) {
            builder.include(marker.getPosition());
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 300));
    }
}
