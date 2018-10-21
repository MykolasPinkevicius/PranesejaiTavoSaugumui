package saugumui.tavo.pranesimas.pranesimastavosaugumui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SwitchCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.gson.Gson;
import com.google.maps.android.heatmaps.Gradient;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static saugumui.tavo.pranesimas.pranesimastavosaugumui.Environment.HOST;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private final int MY_PERMISSIONS_REQUEST_ACCES_FINE_LOCATION = 1;
    private TileOverlay yOverlay;
    private TileOverlay gOverlay;
    private TileOverlay rOverlay;


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

        View mapMenuButton2 = this.findViewById(R.id.action_profile);
        mapMenuButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileActivity();
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
        mMap.moveCamera(CameraUpdateFactory.zoomTo(11));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(54.693465, 25.275827)));

//        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
//            @Override
//            public void onMyLocationChange(Location location) {
//                LatLng vilnius = new LatLng(location.getLatitude(), location.getLongitude());
////                LatLng v=  mMap.getCameraPosition().target ;
//                mMap.addMarker(new MarkerOptions().position(vilnius).title("Marker in Wilno"));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(vilnius));
//
//            }
//        });

//        List<LatLng> list = Arrays.asList(new LatLng(54.7224702, 25.3378288),
//                new LatLng(54.7224702, 25.3388388),
//                new LatLng(54.7224702, 25.3378488),
//                new LatLng(54.7224702, 25.3478588),
//                new LatLng(54.8224702, 25.3378688),
//                new LatLng(55.7224702, 25.3378788));
//
//       final TileOverlay rOverlay = addHeatMap("red", list);
//
//        List<LatLng> list2 = Arrays.asList(new LatLng(54.7324702, 25.3388288),
//                new LatLng(54.7225702, 25.3389388),
//                new LatLng(53.7224702, 25.3578488),
//                new LatLng(54.7284702, 25.3878588),
//                new LatLng(54.8924702, 25.3378988),
//                new LatLng(55.7224702, 25.3378788));
//
//       final TileOverlay yOverlay = addHeatMap("yellow", list2);
//
//        List<LatLng> list3 = Arrays.asList(new LatLng(54.7228702, 25.3378288),
//                new LatLng(54.8224702, 25.3388388),
//                new LatLng(54.7224702, 25.8378488),
//                new LatLng(54.7824702, 25.3478588),
//                new LatLng(54.8224702, 25.3378688),
//                new LatLng(55.7224702, 25.3378788));
//
//        final TileOverlay gOverlay = addHeatMap("green", list3);

//        yOverlay.setVisible(false);
//        gOverlay.setVisible(false);
//        rOverlay.setVisible(false);

        SwitchCompat ySwitch = this.findViewById(R.id.switch1);
        SwitchCompat gSwitch = this.findViewById(R.id.switch2);
        SwitchCompat rSwitch = this.findViewById(R.id.switch3);

        ySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    yOverlay.setVisible(true);
                } else {
                    yOverlay.setVisible(false);
                }

            }
        });

        gSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    gOverlay.setVisible(true);
                } else {
                    gOverlay.setVisible(false);
                }

            }
        });

        rSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    rOverlay.setVisible(true);
                } else {
                    rOverlay.setVisible(false);
                }

            }
        });


        toastMe();
        getReports();



//        LatLng vilnius = new LatLng(myLoc.getLatitude(), myLoc.getLongitude());
//        mMap.addMarker(new MarkerOptions().position(vilnius).title("Marker in Wilno"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(vilnius));
    }

    protected void openReportActivity() {
        Intent myIntent = new Intent(this, CameraAttemptNumberThree.class);
        startActivity(myIntent);
    }

    protected void openProfileActivity() {
        Intent myIntent = new Intent(this, ProfileActivity.class);
        startActivity(myIntent);
    }

    private TileOverlay addHeatMap(String color, List<LatLng> list) {
        int colors[] = new int[2];

        if(color.equals("red")){

        colors[0] = Color.rgb(255, 0, 0);
        colors[1] = Color.rgb(255, 150, 150);

        } else if(color.equals("yellow")){

        colors[0] = Color.rgb(255, 255, 0);
        colors[1] = Color.rgb(255, 255, 150);

        } else {

        colors[0] = Color.rgb(0, 255, 0);
        colors[1] = Color.rgb(150, 255, 150);

        }



        float[] startPoints = {
                0.9f, 1f
        };

        Gradient gradient = new Gradient(colors, startPoints);


        HeatmapTileProvider mProvider = new HeatmapTileProvider.Builder()
                .data(list)
                .gradient(gradient)
                .radius(35)
                .build();
         return mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));

    }

    private void toastMe() {
        Toast toast = Toast.makeText(this, "level up", Toast.LENGTH_LONG);
        toast.show();
    }

    private List<NormalDisturbanceDto> getReports() {
        RequestQueue queue = Volley.newRequestQueue(this);

        final List<NormalDisturbanceDto> NormalDisturbanceDtos = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                HOST + "/v1/disturbances",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String responseBody = new String(response.getBytes());
                        Gson gson = new Gson();
                        NormalDisturbanceDtos.addAll(Arrays.asList(gson.fromJson(responseBody, NormalDisturbanceDto[].class)));
                        addDataToHeat(NormalDisturbanceDtos);
                        // Display the first 500 characters of the response string.
//                                mTextView.setText("Response is: "+ response.substring(0,500));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        mTextView.setText("That didn't work!");
                    }
                }
        );

        queue.add(stringRequest);

        return NormalDisturbanceDtos;
    }

    private void addDataToHeat(List<NormalDisturbanceDto> dist){
//        List<NormalDisturbanceDto> dist = getReports();
        List<LatLng> llR = new ArrayList<>();
        List<LatLng> llY = new ArrayList<>();
        List<LatLng> llG = new ArrayList<>();
        for(NormalDisturbanceDto dd : dist){
            if(dd.getStatus().equals("NEW")) {
                LatLng lr = new LatLng(dd.getLocation().getLatitude(), dd.getLocation().getLongitude());
                llR.add(lr);
            } else if(dd.getStatus().equals("INPROGRESS")) {
                LatLng ly = new LatLng(dd.getLocation().getLatitude(), dd.getLocation().getLongitude());
                llY.add(ly);
            } else {
                LatLng lg = new LatLng(dd.getLocation().getLatitude(), dd.getLocation().getLongitude());
                llG.add(lg);
            }
        }
        rOverlay = addHeatMap("red", llR);
        rOverlay.setVisible(false);
        yOverlay = addHeatMap("yellow", llY);
        yOverlay.setVisible(false);
        gOverlay = addHeatMap("green", llG);
        gOverlay.setVisible(false);
    }

}
