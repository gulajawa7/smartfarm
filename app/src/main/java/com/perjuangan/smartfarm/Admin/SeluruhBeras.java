package com.perjuangan.smartfarm.Admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.perjuangan.smartfarm.CRUD.Input;
import com.perjuangan.smartfarm.CRUD.LihatBeras;
import com.perjuangan.smartfarm.CRUD.UpdateBeras;
import com.perjuangan.smartfarm.Database.DataHelper;
import com.perjuangan.smartfarm.R;

public class SeluruhBeras extends AppCompatActivity {
	String[] daftar,idberas;
	ListView ListView01;
	protected Cursor cursor;
	DataHelper dbcenter;
	public static SeluruhBeras ma;
	EditText theFilter;
	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seluruh_beras);
		Button btn= findViewById(R.id.button2);
		theFilter = (EditText)findViewById(R.id.carinama);

		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent new_data = new Intent(SeluruhBeras.this, Input.class);
				startActivity(new_data);
			}
		});

		ma = this;
		dbcenter = new DataHelper(this);
		RefreshList();

		theFilter.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				(SeluruhBeras.this).adapter.getFilter().filter(s);
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}

	public void RefreshList(){
		SQLiteDatabase db = dbcenter.getReadableDatabase();
		cursor = db.rawQuery("SELECT * FROM gapoktan ORDER BY `nama_beras` ASC",null);
		daftar = new String[cursor.getCount()];
		idberas	= new String[cursor.getCount()];
		cursor.moveToFirst();
		for (int cc=0; cc < cursor.getCount(); cc++){
			cursor.moveToPosition(cc);
			daftar[cc] = cursor.getString(1);
			idberas[cc]	= cursor.getString(0);
		}
		ListView01 		= (ListView)findViewById(R.id.listView1);
		adapter 		= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, daftar);
		ListView01.setAdapter(adapter);
		ListView01.setSelected(true);
		ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {


			public void onItemClick(AdapterView arg0, View arg1, final int arg2, long arg3) {
				final String selection = daftar[arg2]; //.getItemAtPosition(arg2).toString();
				final String idselect	= idberas[arg2];
				final CharSequence[] dialogitem = {"Lihat Data", "Update Data", "Hapus Data"};
				AlertDialog.Builder builder = new AlertDialog.Builder(SeluruhBeras.this);
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
								in.putExtra("referal","SeluruhBeras");
								startActivity(in);
								break;
							case 2 :
								SQLiteDatabase db = dbcenter.getWritableDatabase();
								db.execSQL("DELETE FROM gapoktan where `id` = '"+idselect+"'");
								RefreshList();
								break;
						}
					}
				});
				builder.create().show();
			}});
		((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
	}

}
