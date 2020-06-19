package com.perjuangan.smartfarm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.perjuangan.smartfarm.CRUD.LihatBeras;
import com.perjuangan.smartfarm.CRUD.UpdateBeras;
import com.perjuangan.smartfarm.Database.DataHelper;
import com.perjuangan.smartfarm.Pembeli.Ranking;

import java.util.ArrayList;
import java.util.Collections;

public class RekomendasiMoora extends AppCompatActivity {
	String[] daftar, moora, hasilmoora, iddata;
	ListView ListView01;
	protected Cursor cursor;
	DataHelper dbcenter;
	Button btn_moora;
	Double[] panen, harga, jarak, dbk, dbp, dbka, dbku,dbm, dhama, dcd, dkuan;
	Integer jumlahdata, idberas;
	ArrayAdapter<String> adapter;
	public RekomendasiMoora ma;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rekomendasi_moora);

		Button btn= findViewById(R.id.button2);


		// sama seperti discover rice tetapi langsung dipanggil fungsi tanpa harus klik tombol
		ma = this;
		dbcenter = new DataHelper(this);
		RefreshList();
		Moora();
	}

	public void RefreshList(){
		//Membaca keseluruhan file yang ada pada database
		SQLiteDatabase db = dbcenter.getReadableDatabase();
		cursor = db.rawQuery("SELECT * FROM gapoktan ORDER BY `nama_beras` ASC",null);
		daftar = new String[cursor.getCount()];
		idberas	= new Integer(cursor.getCount());
		iddata  = new String[cursor.getCount()];

		dkuan 	= new Double[cursor.getCount()];
		dbk		= new Double[cursor.getCount()];
		dbp		= new Double[cursor.getCount()];
		dbka		= new Double[cursor.getCount()];
		dbm		= new Double[cursor.getCount()];
		dbku		= new Double[cursor.getCount()];
		dhama		= new Double[cursor.getCount()];
		dcd		= new Double[cursor.getCount()];

		jumlahdata = new Integer(cursor.getCount());

		cursor.moveToFirst();
		for (int cc=0; cc < cursor.getCount(); cc++){
			cursor.moveToPosition(cc);
			daftar[cc] = cursor.getString(1);
			iddata[cc]      = cursor.getString(0);
			Integer dumpku  = Integer.parseInt(cursor.getString(7));

			if(dumpku>200) {
                dkuan[cc]   = Double.parseDouble("4") ;
            } else if(dumpku>=100 && dumpku<=200) {
                dkuan[cc]   = Double.parseDouble("3") ;
            } else {
                dkuan[cc]   = Double.parseDouble("2") ;
            }

            dbk[cc]		= Double.parseDouble(cursor.getString(8));
            dbp[cc]		= Double.parseDouble(cursor.getString(9));
            dbm[cc]		= Double.parseDouble(cursor.getString(10));
            dbka[cc]		= Double.parseDouble(cursor.getString(11));
            dbku[cc]		= Double.parseDouble(cursor.getString(12));
            dhama[cc]	= Double.parseDouble(cursor.getString(13));
            dcd[cc]	 	= Double.parseDouble(cursor.getString(14));


		}
		ListView01 		= (ListView)findViewById(R.id.listView1);
		adapter 		= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, daftar);
		ListView01.setAdapter(adapter);
		ListView01.setSelected(true);
		ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {


			public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
				//final String selection = daftar[arg2]; //.getItemAtPosition(arg2).toString();
                final String idselect	= iddata[arg2];
				final CharSequence[] dialogitem = {"Lihat Data", "Update Data", "Hapus Data"};
				AlertDialog.Builder builder = new AlertDialog.Builder(RekomendasiMoora.this);
				builder.setTitle("Pilihan");
				builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						switch(item){
							case 0 :
								Intent i = new Intent(getApplicationContext(), LihatBeras.class);
								i.putExtra("id", idselect);
								startActivity(i);
								break;
							case 1 :
								Intent in = new Intent(getApplicationContext(), UpdateBeras.class);
								in.putExtra("id", idselect);
								startActivity(in);
								break;
							case 2 :
								SQLiteDatabase db = dbcenter.getWritableDatabase();
								db.execSQL("delete from gapoktan where id = '"+idselect+"'");
								RefreshList();
								break;
						}
					}
				});
				builder.create().show();
			}});
		((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
	}

	public void Moora(){
		Double[][] matriks = new Double[jumlahdata][8];

		for(int d=0;d < jumlahdata;d++) {
			// menghitung kolom baris d kolom 0(kolom panen)
			Double bawah1		= Double.valueOf(0);
			for(int e=0;e<jumlahdata;e++) {
				bawah1	= bawah1 + Math.pow(dbk[e],2);
			}
			Double hasilk1		= dbk[d]/Math.sqrt(bawah1);
			matriks[d][0]		= hasilk1;

			Double bawah2		= Double.valueOf(0);
			for(int e=0;e<jumlahdata;e++) {
				bawah2			= bawah2 + Math.pow(dbp[e],2);
			}
			Double hasilk2		= dbp[d]/Math.sqrt(bawah2);
			matriks[d][1]		= hasilk2;

			Double bawah3		= Double.valueOf(0);
			for(int e=0;e<jumlahdata;e++) {
				bawah3			= bawah3 + Math.pow(dbm[e],2);
			}
			Double hasilk3		= dbm[d]/Math.sqrt(bawah3);
			matriks[d][2]		= hasilk3;

            Double bawah4		= Double.valueOf(0);
            for(int e=0;e<jumlahdata;e++) {
                bawah4			= bawah4 + Math.pow(dbka[e],2);
            }
            Double hasilk4		= dbka[d]/Math.sqrt(bawah4);
            matriks[d][3]		= hasilk4;

            Double bawah5		= Double.valueOf(0);
            for(int e=0;e<jumlahdata;e++) {
                bawah5	= bawah5 + Math.pow(dbku[e],2);
            }
            Double hasilk5		= dbk[d]/Math.sqrt(bawah5);
            matriks[d][4]		= hasilk5;

            Double bawah6		= Double.valueOf(0);
            for(int e=0;e<jumlahdata;e++) {
                bawah6			= bawah6 + Math.pow(dhama[e],2);
            }
            Double hasilk6		= dbp[d]/Math.sqrt(bawah6);
            matriks[d][5]		= hasilk6;

            Double bawah7		= Double.valueOf(0);
            for(int e=0;e<jumlahdata;e++) {
                bawah7			= bawah7 + Math.pow(dcd[e],2);
            }
            Double hasilk7		= dbm[d]/Math.sqrt(bawah7);
            matriks[d][6]		= hasilk7;

            Double bawah8		= Double.valueOf(0);
            for(int e=0;e<jumlahdata;e++) {
                bawah8			= bawah8 + Math.pow(dkuan[e],2);
            }
            Double hasilk8		= dbka[d]/Math.sqrt(bawah8);
            matriks[d][7]		= hasilk8;

		}

		Log.d("Matrik :", matriks[0][0].toString());

		ArrayList<Ranking> rank = new ArrayList<>();

		for(int f=0;f<jumlahdata;f++) {
			Double hasilp		= matriks[f][0] + matriks[f][7] - matriks[f][1] - matriks[f][2] - matriks[f][3] - matriks[f][4] - matriks[f][5] - matriks[f][6];
			rank.add(new Ranking(f,hasilp));
		}

		Collections.sort(rank,Ranking.RanCompare);

		moora		= new String[jumlahdata];
		hasilmoora  = new String[jumlahdata];
		for(int h=0;h<jumlahdata;h++) {
			moora[h]	= daftar[rank.get(h).getNum()];
			hasilmoora[h]   = iddata[rank.get(h).getNum()];
			Log.d("Hasil Pengurutan", "Nama : " + daftar[rank.get(h).getNum()]);
		}

		ListView01 		= findViewById(R.id.listView1);
		adapter 		= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, moora);
		ListView01.setAdapter(adapter);
		ListView01.setSelected(true);

		//ListView dapat diklik untuk membaca file secara detail
		ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
				final String selection = moora[arg2]; //.getItemAtPosition(arg2).toString();
                final String idselect   = hasilmoora[arg2];
				//Memunculkan fitur alert dialog yang berupa popup
				final CharSequence[] dialogitem = {"Lihat Data"};
				AlertDialog.Builder builder = new AlertDialog.Builder(RekomendasiMoora.this);
				builder.setTitle("Pilihan");
				builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						if (item == 0) {
							Intent i = new Intent(getApplicationContext(), LihatBeras.class);
							i.putExtra("id", idselect);
							startActivity(i);
						}
					}
				});
				builder.create().show();
			}});
		((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
	}

}
