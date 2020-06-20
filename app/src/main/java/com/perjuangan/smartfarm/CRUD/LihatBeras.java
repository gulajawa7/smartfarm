package com.perjuangan.smartfarm.CRUD;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.perjuangan.smartfarm.Database.DataHelper;
import com.perjuangan.smartfarm.R;

public class LihatBeras extends AppCompatActivity implements OnMapReadyCallback {
	protected Cursor cursor;
	DataHelper dbHelper;
	TextView no, name, price, description, penjual, kuantitas, type, year, bk, bka, bku, bm, bp, ha, cd;
	Button back;
	private GoogleMap mMap;
	private String latValue, longValue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lihat_beras);

		initMap();

		dbHelper = new DataHelper(this);
		no = findViewById(R.id.txt_detail_no);
		name = findViewById(R.id.txt_detail_nama);
		type = findViewById(R.id.txt_detail_jenis);
		year = findViewById(R.id.txt_detail_tahun);
		price = findViewById(R.id.txt_detail_harga);
		description = findViewById(R.id.txt_detail_deskripsi);
		penjual = findViewById(R.id.txt_detail_penjual);
		kuantitas = findViewById(R.id.txt_kuantitas_bers);
		bk		= findViewById(R.id.txt_bk);
		bka		= findViewById(R.id.txt_bka);
		bp		= findViewById(R.id.txt_bp);
		bm		= findViewById(R.id.txt_bm);
		bku		= findViewById(R.id.txt_bku);
		ha		= findViewById(R.id.txt_ha);
		cd		= findViewById(R.id.txt_cd);

		SQLiteDatabase db = dbHelper.getReadableDatabase();
		cursor = db.rawQuery("SELECT * FROM gapoktan WHERE `id` = '" +
				getIntent().getStringExtra("id") + "'",null);
		cursor.moveToFirst();
		if (cursor.getCount()>0)
		{
			cursor.moveToPosition(0);
			no.setText(cursor.getString(0));
			name.setText(cursor.getString(1));
			price.setText(cursor.getString(4));
			description.setText(cursor.getString(5));
			penjual.setText(cursor.getString(6));
			kuantitas.setText(cursor.getString(7));
			type.setText(cursor.getString(2));
			year.setText(cursor.getString(3));
			String sbk		= cursor.getString(8);
			String sbp		= cursor.getString(9);
			String sbm		= cursor.getString(10);
			String sbka		= cursor.getString(11);
			String sbku		= cursor.getString(12);
			String shama	= cursor.getString(13);
			String scd	 	= cursor.getString(14);
			latValue = cursor.getString(15);
			longValue = cursor.getString(16);

			Log.d("Hasil", sbk+sbp+sbm+sbka+sbku+shama+scd);

			if(sbk.equals("5")) {
				bk.setText("95%");
			} else if(sbk.equals("4")) {
				bk.setText("78%");
			} else if(sbk.equals("3")) {
				bk.setText("75%");
			} else {
				bk.setText("60%");
			}

			if(sbp.equals("5")) {
				bp.setText("5%");
			} else if(sbp.equals("4")) {
				bp.setText("20%");
			} else if(sbp.equals("3")) {
				bp.setText("25%");
			} else {
				bp.setText("35%");
			}
			if(sbm.equals("5")) {
				bm.setText("0%");
			} else if(sbm.equals("4")) {
				bm.setText("2%");
			} else if(sbm.equals("3")) {
				bm.setText("3%");
			} else {
				bm.setText("5%");
			}
			if(sbka.equals("5")) {
				bka.setText("0%");
			} else if(sbka.equals("4")) {
				bka.setText("2%");
			} else if(sbka.equals("3")) {
				bka.setText("3%");
			} else {
				bka.setText("5%");
			}
			if(sbku.equals("5")) {
				bku.setText("0%");
			} else if(sbku.equals("4")) {
				bku.setText("2%");
			} else if(sbku.equals("3")) {
				bku.setText("3%");
			} else {
				bku.setText("5%");
			}
			if(shama.equals("5")) {
				ha.setText("0%");
			} else if(shama.equals("4")) {
				ha.setText("0.02%");
			} else if(shama.equals("3")) {
				ha.setText("0.05%");
			} else {
				ha.setText("0.2%");
			}
			if(scd.equals("5")) {
				cd.setText("0%");
			} else if(scd.equals("4")) {
                cd.setText("1%");
			} else if(scd.equals("3")) {
                cd.setText("2%");
			} else {
                cd.setText("3%");
			}
		}
		back = (Button) findViewById(R.id.btn_back);
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}

	public void initMap() {
		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		if(mapFragment != null) mapFragment.getMapAsync(this);
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		mMap = googleMap;
		updateMap(latValue, longValue);
	}

	public void updateMap(String latValue, String longValue) {
		Log.e("testing","testing");
		Log.e("check", "Checking lat long" + latValue + longValue);
		LatLng currentCoordinate = new LatLng(Double.parseDouble(latValue), Double.parseDouble(longValue));
		mMap.addMarker(new MarkerOptions().position(currentCoordinate).title("Current Map"));
		mMap.moveCamera(CameraUpdateFactory.newLatLng(currentCoordinate));
		mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );
	}

}
