package com.ian.igoand.mibb;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ObslugaMapy extends FragmentActivity implements OnMapReadyCallback {

    Obserwacja obserwacja;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obsluga_mapy);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapLokalizacja);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        Bundle zmienneLokalizacyjne = getIntent().getExtras();
        double x = 0, y = 0;
        String miejscowosc = "";
        float przyblizenie = 14;
        if (zmienneLokalizacyjne != null) {
            x = (double) zmienneLokalizacyjne.get("x");
            y = (double) zmienneLokalizacyjne.get("y");
            miejscowosc = zmienneLokalizacyjne.getString("pointer");
        }

        LatLng lokalizajcaObserwacji = new LatLng(x, y);
        mMap.addMarker(new MarkerOptions().position(lokalizajcaObserwacji).title(miejscowosc));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lokalizajcaObserwacji, przyblizenie));
    }
}
