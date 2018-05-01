package com.ian.igoand.mibb;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ObslugaGPS implements LocationListener {

    public static String szerokosc, wysokosc, miasto;
    Obserwacja obserwacja = new Obserwacja();
    /*    public Boolean pokazStatusGPS() {
            ContentResolver contentResolver = kontekst.getContentResolver();
            boolean statuGPS = Settings.Secure.isLocationProviderEnabled(contentResolver, LocationManager.GPS_PROVIDER);
            if (statuGPS) {
                return true;
            } else {
                return false;
            }
        }*/
    public static String dajDaneGPS() {
        return "Szerokość " + szerokosc + ", wysokość " + wysokosc + ". Nazwa miejscowosci: " + miasto;
    }

    @Override
    public void onLocationChanged(Location location) {

        location.getLatitude();
        location.getLongitude();
        szerokosc = String.valueOf(location.getLatitude());
        wysokosc = String.valueOf(location.getLongitude());

        Geocoder geocoder = new Geocoder(obserwacja.context, Locale.getDefault());
        List<Address> adresy;
        try {
            adresy = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (adresy.size() > 0)
                System.out.print(adresy.get(0).getLocality());
            miasto = adresy.get(0).getLocality();
        } catch (IOException e) {
            e.printStackTrace();
        }
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




