package net.Strikers.strikers_app;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

//import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import net.Strikers.strikers_app.databinding.ActivityMapsBinding;

import java.util.Vector;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback

    {

    private GoogleMap mMap;

        //MarkerOptions marker;
    LatLng centerlocation;

    Vector<MarkerOptions> markerOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //private FusedLocationProviderClient mFusedLocationProviderClient;
        net.Strikers.strikers_app.databinding.ActivityMapsBinding binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        centerlocation = new LatLng(3.1528,101.7038);

        markerOptions = new Vector<>();
        markerOptions.add(new MarkerOptions().title("Kuala Lumpur Tower")
                .position(new LatLng(3.1528,101.7038))
                .snippet("Open during MCO : 8am - 6pm")

        );

        markerOptions.add(new MarkerOptions().title("Twin Towers Medical Centre")
                .position(new LatLng(3.1596009243216088,101.71510581338278))
                .snippet("Contact us: +60323823500")

        );

        markerOptions.add(new MarkerOptions().title("Queens Medical Clinic")
                .position(new LatLng(3.155487305926333,101.70992254929116))
                .snippet("Contact us: +60327154442")

        );

        markerOptions.add(new MarkerOptions().title("Group Poliklinik Kumpulan City Wisma Central, Jalan Ampang")
                .position(new LatLng(3.1599437251189353,101.71991233196295))
                .snippet("Contact us: +60321617105")

        );
        markerOptions.add(new MarkerOptions().title("Hospital Kepala Batas")
                .position(new LatLng(5.512902797119672,100.43651925250076))
                .snippet("Operation Hours: 24Hours")
        );
        markerOptions.add(new MarkerOptions().title("Penang General Hospital")
                .position(new LatLng(5.417254333523842,100.31147035434802))
                .snippet("Operation Hours: 24Hours")
        );
        markerOptions.add(new MarkerOptions().title("Tapah Hospital")
                .position(new LatLng(4.260105992530227,101.24586921869923))
                .snippet("Operation Hours: 24Hours")
        );
        markerOptions.add(new MarkerOptions().title("Hospital Kuala Lumpur")
                .position(new LatLng(3.175392350787756,101.69131809707922))
                .snippet("Operation Hours: 24Hours")
        );

        markerOptions.add(new MarkerOptions().title("Hospital Kulim")
                .position(new LatLng(5.3931,100.5730))
                .snippet("Operation Hours: 24Hours")
        );
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

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        for(MarkerOptions mark: markerOptions){
            mMap.addMarker(mark);
        }

        enableMyLocation();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerlocation, 13));

    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
            }
        } else {
            String[] perms = {"android.permission.ACCESS_FINE_LOCATION"};

            // Permission to access the location is missing. Show rationale and request permission
            ActivityCompat.requestPermissions(this,perms, 200);
        }

    }
}