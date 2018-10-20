package saugumui.tavo.pranesimas.pranesimastavosaugumui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private final int MY_PERMISSIONS_REQUEST_ACCES_FINE_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        View mapMenuButton = this.findViewById(R.id.action_report);
        mapMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReportActivity();
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCES_FINE_LOCATION);
            }
        }
        mMap.setMyLocationEnabled(true);
//        Location myLocation = mMap.getMyLocation();
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(myLocation.getLatitude(), myLocation.getLongitude())));

//        LocationSource.OnLocationChangedListener listener = new LocationSource.OnLocationChangedListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//                LatLng vilnius = new LatLng(location.getLatitude(), location.getLongitude());
//                LatLng v=  mMap.getCameraPosition().target ;
//                mMap.addMarker(new MarkerOptions().position(v).title("Marker in Wilno"));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(v));
//
//            }
//        };

        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                LatLng vilnius = new LatLng(location.getLatitude(), location.getLongitude());
//                LatLng v=  mMap.getCameraPosition().target ;
                mMap.addMarker(new MarkerOptions().position(vilnius).title("Marker in Wilno"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(vilnius));
                mMap.moveCamera(CameraUpdateFactory.zoomTo(11));

            }
        });


//        LatLng vilnius = new LatLng(myLoc.getLatitude(), myLoc.getLongitude());
//        mMap.addMarker(new MarkerOptions().position(vilnius).title("Marker in Wilno"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(vilnius));
    }

    protected void openReportActivity() {
        Intent myIntent = new Intent(this, CameraAttemptNumberThree.class);
        startActivity(myIntent);
    }
}
