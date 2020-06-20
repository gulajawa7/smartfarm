package com.perjuangan.smartfarm;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.perjuangan.smartfarm.Admin.MainActivity;
import com.perjuangan.smartfarm.Pembeli.PembeliActivity;

public class Splashscreen extends AppCompatActivity {
	private int waktu_loading = 4000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen);

		final SesiLogin sesi = new SesiLogin(this);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// digunakan untuk menyimpan sesi, selain itu untuk redirect jika sesi belum dibuat/login
				Intent login;
				if(sesi.getIsLogin()==true) {
					login = new Intent(Splashscreen.this, MainActivity.class);
				} else {
					login = new Intent(Splashscreen.this, PembeliActivity.class);
				}
				startActivity(login);
				finish();
			}
		},waktu_loading);
	}
}
