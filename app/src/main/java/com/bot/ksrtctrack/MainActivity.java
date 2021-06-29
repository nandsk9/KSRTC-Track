package com.bot.ksrtctrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    Button btnStart, btnStop;
    GoogleMap ggleMap;
    MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.start_button);
        btnStop = findViewById(R.id.stop_button);
        mapView = findViewById(R.id.mapView1);
        mapView.onCreate(null);
        mapView.getMapAsync(this);
        mapView.setEnabled(true);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ggleMap!=null){
                    LatLng ll2 = new LatLng(12.411063099999993,75.0225179);
                    ggleMap.animateCamera(CameraUpdateFactory.newLatLng(ll2));
                    ggleMap.addMarker(new MarkerOptions().position(ll2).title("Thrikkanand").icon(getBitMap(MainActivity.this,R.drawable.bus_marker)));
                }
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        ggleMap = googleMap;
        ggleMap.setMinZoomPreference(10);
        LatLng ll1 = new LatLng(12.3140248,75.0875445);
        ggleMap.animateCamera(CameraUpdateFactory.newLatLng(ll1));
        ggleMap.addMarker(new MarkerOptions().position(ll1).title("Kanhagad Bus Stand"));
        LatLng ll3 = new LatLng(12.4999548,74.9819596);
        ggleMap.animateCamera(CameraUpdateFactory.newLatLng(ll3));
        ggleMap.addMarker(new MarkerOptions().position(ll3).title("Kasaragod Bus Stand"));
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    private BitmapDescriptor getBitMap(Context context, int resId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, resId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}