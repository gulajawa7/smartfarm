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
import android.widget.Toast;

import com.perjuangan.smartfarm.Admin.SeluruhBeras;
import com.perjuangan.smartfarm.Database.DataHelper;
import com.perjuangan.smartfarm.R;

import java.util.ArrayList;
import java.util.List;

public class Input extends AppCompatActivity {
	protected Cursor cursor;
	DataHelper dbHelper;
	RadioGroup b_kepala, b_patah, b_menir, b_kapur, b_kuning, b_asing, c_dedak;
	RadioButton bk1, bk2, bk3, bk4, bp1, bp2, bp3, bp4, bm1, bm2, bm3;
	EditText name, price, description, penjual, kuantitas;
	Spinner jenis, year;
	Button save;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input);
		dbHelper = new DataHelper(this);

		name = (EditText)findViewById(R.id.edt_input_nama);
		price = (EditText)findViewById(R.id.edt_input_harga);
		description = (EditText)findViewById(R.id. edt_input_deskripsi);
		penjual = (EditText)findViewById(R.id.edt_input_nama_gapoktan);
		kuantitas = (EditText)findViewById(R.id.edt_input_kuantitas_beras);

//		Radio Group
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


		jenis = (Spinner)findViewById(R.id.spinnerType);
		year = (Spinner)findViewById(R.id.spinnerYear);
		save = (Button)findViewById(R.id.simpan_input);

		// membuat dropdown jenis bras
		List<String> typeberas = new ArrayList<String>();
		typeberas.add("Beras Putih");
		typeberas.add("Beras Merah");
		typeberas.add("Beras Hitam");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, typeberas);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		jenis.setAdapter(dataAdapter);

		// membuat dropdown tahun panen
		List<String> taunberas = new ArrayList<String>();
		for(int t=2019;t>2012;t--) {
			taunberas.add(String.valueOf(t));
		}
		ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, taunberas);
		dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		year.setAdapter(dataAdapter2);


		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final String nitype		= String.valueOf(jenis.getSelectedItem());
				final String nitaun		= String.valueOf(year.getSelectedItem());
				String namaberas		= name.getText().toString();
				String jenisberas		= nitype;
				String tahunpanen		= nitaun;
				String hargapanen		= price.getText().toString();
				String deskripsi		= description.getText().toString();
				String gapoktan			= penjual.getText().toString();
				String kuantitasberas	= kuantitas.getText().toString();
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


			    // insert data ke database
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				String sql		= "INSERT INTO gapoktan ( `nama_beras`,`jenis_beras`,`tahun_panen`,`harga_panen`,`deskripsi`,`gapoktan`,`kuantitas`, `butir_kepala`, `butir_patah`, `butir_menir`, `butir_kapur`, `butir_kuning`, `hama_penyakit`, `dedak_bekatul`)" +
						" VALUES ('"+ namaberas +
						"','" + jenisberas +
						"','" + tahunpanen +
						"','" + hargapanen +
						"','" + deskripsi +
						"','" + gapoktan +
						"','" + kuantitasberas +
						"','" + butirkepala + "','" + butirpecah + "','" + butirmenir + "','" + butirkapur + "','" + butirkuning + "','" + hama + "','" + campurancempedak +
						"')";
				Log.d("SQL",sql);
				db.execSQL(sql);
				Toast.makeText(getApplicationContext(), "Data disimpan", Toast.LENGTH_LONG).show();
				SeluruhBeras.ma.RefreshList();
				finish();
			}
		});
	}
}
