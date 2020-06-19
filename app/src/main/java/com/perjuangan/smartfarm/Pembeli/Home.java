package com.perjuangan.smartfarm.Pembeli;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.perjuangan.smartfarm.R;

public class Home extends Fragment {
	CardView cv_putih, cv_merah, cv_hitam, cv_semua, brsputih, brsmerah, brshitam;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_home, container, false);
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		cv_putih = view.findViewById(R.id.cv_pembeli_putih);
		cv_putih.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent pembeli_putih = new Intent(getActivity(), Discover_Rice_Putih.class);
				startActivity(pembeli_putih);
			}
		});

		cv_merah = view.findViewById(R.id.cv_pembeli_merah);
		cv_merah.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent pembeli_merah = new Intent(getActivity(), Discover_Rice_Merah.class);
				startActivity(pembeli_merah);
			}
		});

		cv_hitam = view.findViewById(R.id.cv_pembeli_hitam);
		cv_hitam.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent pembeli_hitam = new Intent(getActivity(), Discover_Rice_Hitam.class);
				startActivity(pembeli_hitam);
			}
		});

		cv_semua = view.findViewById(R.id.cv_pembeli_semua);
		cv_semua.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent semua_pembeli = new Intent(getActivity(), Discover_Rice.class);
				startActivity(semua_pembeli);
			}
		});

		brsputih	= view.findViewById(R.id.brsputih);
		brsputih.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent brs		= new Intent(getActivity(), Deskripsi.class);
				brs.putExtra("jenis","putih");
				startActivity(brs);
			}
		});

		brsmerah	= view.findViewById(R.id.brsmerah);
		brsmerah.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent brs		= new Intent(getActivity(), Deskripsi.class);
				brs.putExtra("jenis","merah");
				startActivity(brs);
			}
		});

		brshitam	= view.findViewById(R.id.brshitam);
		brshitam.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent brs		= new Intent(getActivity(), Deskripsi.class);
				brs.putExtra("jenis","hitam");
				startActivity(brs);
			}
		});

	}
}
