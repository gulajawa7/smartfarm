package com.perjuangan.smartfarm.Pembeli;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.perjuangan.smartfarm.Admin.MainActivity;
import com.perjuangan.smartfarm.R;
import com.perjuangan.smartfarm.SesiLogin;

public class PembeliActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pembeli);

		SesiLogin sesi = new SesiLogin(this);

		Intent login;
		if(sesi.getIsLogin()==true) {
			login = new Intent(PembeliActivity.this, MainActivity.class);
			startActivity(login);
			finish();
		}
		// kita set default nya HomeAdmin Fragment
		loadFragment(new Home());
		// inisialisasi BottomNavigaionView
		BottomNavigationView bottomNavigationView = findViewById(R.id.bn_main);
		// beri listener pada saat item/menu bottomnavigation terpilih
		bottomNavigationView.setOnNavigationItemSelectedListener(this);
	}
	private boolean loadFragment(Fragment fragment) {
		if (fragment != null) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fl_container, fragment)
					.commit();
			return true;
		}
		return false;
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
		Fragment fragment = null;
		switch (menuItem.getItemId()){
			case R.id.home:
				fragment = new Home();
				break;
			case R.id.account_menu:
				fragment = new Login();
				break;
		}
		return loadFragment(fragment);

	}

}
