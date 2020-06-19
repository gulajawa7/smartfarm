package com.perjuangan.smartfarm.CRUD;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.perjuangan.smartfarm.Admin.BerasHitam;
import com.perjuangan.smartfarm.Admin.BerasMerah;
import com.perjuangan.smartfarm.Admin.BerasPutih;
import com.perjuangan.smartfarm.Admin.SeluruhBeras;
import com.perjuangan.smartfarm.Database.DataHelper;
import com.perjuangan.smartfarm.R;

import java.util.ArrayList;
import java.util.List;

public class UpdateBeras extends AppCompatActivity {
	protected Cursor cursor;
	DataHelper dbHelper;
	TextView update_beras;
	RadioGroup b_kepala, b_patah, b_menir, b_kapur, b_kuning, b_asing, c_dedak;
	EditText no, name, price, description, penjual, kuantitas;
	Spinner type, year;
	Button save;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_beras);
		dbHelper = new DataHelper(this);

		name 		= (EditText)findViewById(R.id.edt_input_nama);
		price 		= (EditText)findViewById(R.id.edt_input_harga);
		description = (EditText)findViewById(R.id. edt_input_deskripsi);
		penjual 	= (EditText)findViewById(R.id.edt_input_nama_penjual);
		kuantitas 		= (EditText)findViewById(R.id.edt_input_kuantitas_beras);

		type = (Spinner)findViewById(R.id.spinnerType);
		year = (Spinner)findViewById(R.id.spinnerYear);

		//Radio Group
		b_kepala = findViewById(R.id.b_kepala);
		b_patah = findViewById(R.id.b_patah);
		b_menir = findViewById(R.id.b_menir);
		b_kapur = findViewById(R.id.b_kapur);
		b_kuning = findViewById(R.id.b_kuning);
		b_asing = findViewById(R.id.b_asing);
		c_dedak = findViewById(R.id.c_dedak);

		// Radio Btn
		final RadioButton bk2		= (RadioButton)findViewById(R.id.bk2);
		final RadioButton bk3		= (RadioButton)findViewById(R.id.bk3);
		final RadioButton bk4		= (RadioButton)findViewById(R.id.bk4);
		final RadioButton bk5		= (RadioButton)findViewById(R.id.bk5);
		final RadioButton bp5		= (RadioButton)findViewById(R.id.bp5);
		final RadioButton bp4		= (RadioButton)findViewById(R.id.bp4);
		final RadioButton bp3		= (RadioButton)findViewById(R.id.bp3);
		final RadioButton bp2		= (RadioButton)findViewById(R.id.bp2);
		final RadioButton bm5		= (RadioButton)findViewById(R.id.bm5);
		final RadioButton bm4		= (RadioButton)findViewById(R.id.bm4);
		final RadioButton bm3		= (RadioButton)findViewById(R.id.bm3);
		final RadioButton bm2		= (RadioButton)findViewById(R.id.bm2);
		final RadioButton bka5		= (RadioButton)findViewById(R.id.bka5);
		final RadioButton bka4		= (RadioButton)findViewById(R.id.bka4);
		final RadioButton bka3		= (RadioButton)findViewById(R.id.bka3);
		final RadioButton bka2		= (RadioButton)findViewById(R.id.bka2);
		final RadioButton bku5		= (RadioButton)findViewById(R.id.bku5);
		final RadioButton bku4		= (RadioButton)findViewById(R.id.bku4);
		final RadioButton bku3		= (RadioButton)findViewById(R.id.bku3);
		final RadioButton bku2		= (RadioButton)findViewById(R.id.bku2);
		final RadioButton ha5		= (RadioButton)findViewById(R.id.ha5);
		final RadioButton ha4		= (RadioButton)findViewById(R.id.ha4);
		final RadioButton ha3		= (RadioButton)findViewById(R.id.ha3);
		final RadioButton ha2		= (RadioButton)findViewById(R.id.ha2);
		final RadioButton cd5		= (RadioButton)findViewById(R.id.cd5);
		final RadioButton cd4		= (RadioButton)findViewById(R.id.cd4);
		final RadioButton cd3		= (RadioButton)findViewById(R.id.cd3);
		final RadioButton cd2		= (RadioButton)findViewById(R.id.cd2);

		save = (Button)findViewById(R.id.simpan_input);

		List<String> typeberas = new ArrayList<String>();
		typeberas.add("Beras Putih");
		typeberas.add("Beras Merah");
		typeberas.add("Beras Hitam");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, typeberas);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		type.setAdapter(dataAdapter);

		List<String> taunberas = new ArrayList<String>();
		for(int t=2019;t>2012;t--) {
			taunberas.add(String.valueOf(t));
		}
		ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, taunberas);
		dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		year.setAdapter(dataAdapter2);

		SQLiteDatabase db = dbHelper.getReadableDatabase();
		cursor = db.rawQuery("SELECT * FROM gapoktan WHERE `id` = '" +
				getIntent().getStringExtra("id") + "'",null);
		cursor.moveToFirst();
		if (cursor.getCount()>0) {
			cursor.moveToPosition(0);

			name.setText(cursor.getString(1));
			price.setText(cursor.getString(4));
			description.setText(cursor.getString(5));
			penjual.setText(cursor.getString(6));
			kuantitas.setText(cursor.getString(7));

			// menset data yang telah ada didatabase
			type.setSelection(typeberas.indexOf(cursor.getString(2)));
			year.setSelection(taunberas.indexOf(cursor.getString(3)));

			String sbk		= cursor.getString(8);
			String sbp		= cursor.getString(9);
			String sbm		= cursor.getString(10);
			String sbka		= cursor.getString(11);
			String sbku		= cursor.getString(12);
			String shama	= cursor.getString(13);
			String scd	 	= cursor.getString(14);

			Log.d("Hasil", sbk+sbp+sbm+sbka+sbku+shama+scd);

			if(sbk.equals("5")) {
				bk5.setChecked(true);
			} else if(sbk.equals("4")) {
				bk4.setChecked(true);
			} else if(sbk.equals("3")) {
				bk3.setChecked(true);
			} else {
				bk2.setChecked(true);
			}

			if(sbp.equals("5")) {
				bp5.setChecked(true);
			} else if(sbp.equals("4")) {
				bp4.setChecked(true);
			} else if(sbp.equals("3")) {
				bp3.setChecked(true);
			} else {
				bp2.setChecked(true);
			}
			if(sbm.equals("5")) {
				bm5.setChecked(true);
			} else if(sbm.equals("4")) {
				bm4.setChecked(true);
			} else if(sbm.equals("3")) {
				bm3.setChecked(true);
			} else {
				bm2.setChecked(true);
			}
			if(sbka.equals("5")) {
				bka5.setChecked(true);
			} else if(sbka.equals("4")) {
				bka4.setChecked(true);
			} else if(sbka.equals("3")) {
				bka3.setChecked(true);
			} else {
				bka2.setChecked(true);
			}
			if(sbku.equals("5")) {
				bku5.setChecked(true);
			} else if(sbku.equals("4")) {
				bku4.setChecked(true);
			} else if(sbku.equals("3")) {
				bku3.setChecked(true);
			} else {
				bku2.setChecked(true);
			}
			if(shama.equals("5")) {
				ha5.setChecked(true);
			} else if(shama.equals("4")) {
				ha4.setChecked(true);
			} else if(shama.equals("3")) {
				ha3.setChecked(true);
			} else {
				ha2.setChecked(true);
			}
			if(scd.equals("5")) {
				cd5.setChecked(true);
			} else if(scd.equals("4")) {
				cd4.setChecked(true);
			} else if(scd.equals("3")) {
				cd3.setChecked(true);
			} else {
				cd2.setChecked(true);
			}


		}

		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final String nitype		= String.valueOf(type.getSelectedItem());
				final String nitaun		= String.valueOf(year.getSelectedItem());
				String namaberas		= name.getText().toString();
				String jenisberas		= nitype;
				String tahunpanen		= nitaun;
				String hargapanen		= price.getText().toString();
				String deskripsi		= description.getText().toString();
				String gapoktan			= penjual.getText().toString();
				String kuantitasberas	= kuantitas.getText().toString();
				//membuat variabel penampung pilihan radio button
				//membuat variabel penampung pilihan radio button
				int selectbk			= b_kepala.getCheckedRadioButtonId();
				int selectbp			= b_patah.getCheckedRadioButtonId();
				int selectbku			= b_kuning.getCheckedRadioButtonId();
				int selectbka			= b_kapur.getCheckedRadioButtonId();
				int selectbm			= b_menir.getCheckedRadioButtonId();
				int selectha			= b_asing.getCheckedRadioButtonId();
				int selectcd			= c_dedak.getCheckedRadioButtonId();

				// benda asing(hama)
				String butirkepala, butirpecah, butirkapur, butirkuning, butirmenir, hama, campurancempedak;

				if(selectbk==bk5.getId()) {
					butirkepala	= "5";
				} else if(selectbk==bk4.getId()) {
					butirkepala = "4";
				} else if(selectbk==bk3.getId()) {
					butirkepala = "3";
				} else {
					butirkepala = "2";
				}

				if(selectbp==bp5.getId()) {
					butirpecah	= "5";
				} else if(selectbp==bp4.getId()) {
					butirpecah = "4";
				} else if(selectbp==bp3.getId()) {
					butirpecah = "3";
				} else {
					butirpecah = "2";
				}

				if(selectbka==bka5.getId()) {
					butirkapur	= "5";
				} else if(selectbka==bka4.getId()) {
					butirkapur = "4";
				} else if(selectbka==bka3.getId()) {
					butirkapur = "3";
				} else {
					butirkapur = "2";
				}

				if(selectbku==bku5.getId()) {
					butirkuning	= "5";
				} else if(selectbku==bku4.getId()) {
					butirkuning = "4";
				} else if(selectbku==bku3.getId()) {
					butirkuning = "3";
				} else {
					butirkuning = "2";
				}

				if(selectbm==bm5.getId()) {
					butirmenir	= "5";
				} else if(selectbm==bm4.getId()) {
					butirmenir = "4";
				} else if(selectbm==bm3.getId()) {
					butirmenir = "3";
				} else {
					butirmenir = "2";
				}

				if(selectha==ha5.getId()) {
					hama	= "5";
				} else if(selectha==ha4.getId()) {
					hama = "4";
				} else if(selectha==ha3.getId()) {
					hama = "3";
				} else {
					hama = "2";
				}

				if(selectcd==cd5.getId()) {
					campurancempedak	= "5";
				} else if(selectcd==cd4.getId()) {
					campurancempedak = "4";
				} else if(selectcd==cd3.getId()) {
					campurancempedak = "3";
				} else {
					campurancempedak = "2";
				}

				SQLiteDatabase db = dbHelper.getWritableDatabase();
				String squery = "UPDATE gapoktan SET `nama_beras`='"+ namaberas +"', `jenis_beras`='" + jenisberas +
						"', `tahun_panen`='" + tahunpanen +
						"', `harga_panen`='" + hargapanen +
						"', deskripsi='" + deskripsi +
						"', `gapoktan`='" + gapoktan +
						"', `kuantitas`='" + kuantitasberas +
						"', `butir_kepala`='"+ butirkepala +
						"', `butir_patah`='" + butirpecah +
						"', `butir_menir`='" + butirmenir +
						"', `butir_kapur`='" + butirkapur +
						"', `butir_kuning`='" + butirkuning +
						"', `hama_penyakit`='"+ hama +
						"', `dedak_bekatul`='"+ campurancempedak +
						"' WHERE `id`='" + getIntent().getStringExtra("id") + "'";
				Log.d("SQL", squery);
				db.execSQL(squery);
				Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
				String refe		= getIntent().getStringExtra("referal");
				if(refe.equals("BerasPutih")) {
					BerasPutih.ma.RefreshList();
				} else if(refe.equals("BerasMerah")) {
					BerasMerah.ma.RefreshList();
				} else if(refe.equals("BerasHitam")) {
					BerasHitam.ma.RefreshList();
				} else {
					SeluruhBeras.ma.RefreshList();
				}
				finish();
			}
		});

	}
}
