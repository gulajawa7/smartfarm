package com.perjuangan.smartfarm.Admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

import com.perjuangan.smartfarm.Pembeli.PembeliActivity;
import com.perjuangan.smartfarm.R;
import com.perjuangan.smartfarm.RekomendasiMoora;
import com.perjuangan.smartfarm.SesiLogin;

public class MainActivity extends AppCompatActivity {
	GridLayout mainGrid;
	CardView cd_rekomendasi, cd_semua,
			cd_putih, cd_merah,
			cd_hitam, cd_input,
			cd_about, cd_logout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mainGrid = findViewById(R.id.mainGrid);

		final SesiLogin sesi = new SesiLogin(this);

		if(sesi.getIsLogin()==false) {
			Intent pembeli		= new Intent(MainActivity.this, PembeliActivity.class);
			startActivity(pembeli);
		}

		cd_rekomendasi = findViewById(R.id.card_rekomendasi);
		cd_rekomendasi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent moora = new Intent(MainActivity.this, RekomendasiMoora.class);
				startActivity(moora);
			}
		});
		cd_semua = findViewById(R.id.card_semua);
		cd_semua.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent all_beras = new Intent(MainActivity.this, SeluruhBeras.class);
				startActivity(all_beras);
			}
		});

		cd_putih = findViewById(R.id.card_putih);
		cd_putih.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent beras_putih = new Intent(MainActivity.this, BerasPutih.class);
				startActivity(beras_putih);
			}
		});

		cd_merah = findViewById(R.id.card_merah);
		cd_merah.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent beras_merah = new Intent(MainActivity.this, BerasMerah.class);
				startActivity(beras_merah);
			}
		});

		cd_hitam = findViewById(R.id.card_hitam);
		cd_hitam.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent beras_hitam = new Intent(MainActivity.this, BerasHitam.class);
				startActivity(beras_hitam);
			}
		});

		cd_about = findViewById(R.id.card_about);
		cd_about.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent activity_about = new Intent(MainActivity.this, about.class);
				startActivity(activity_about);
			}
		});

		cd_logout = findViewById(R.id.card_logout);
		cd_logout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setMessage("Apakah anda yakin ingin keluar?")
						.setCancelable(false)
						.setPositiveButton("Yakin", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// untuk logout
								sesi.putIsLogin(false);
								Intent logoutproses = new Intent(MainActivity.this, PembeliActivity.class);
								startActivity(logoutproses);
								finish();
							}
						})
						.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
				AlertDialog alert = builder.create();
				alert.show();
			}
		});

	}


}
