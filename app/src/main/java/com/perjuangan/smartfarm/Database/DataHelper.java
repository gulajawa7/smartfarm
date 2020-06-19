package com.perjuangan.smartfarm.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "databeras.db";
	private static final int DATABASE_VERSION = 1;
	Cursor cursor;
	public DataHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// digunakan untuk membuat tabel awal
		String sql 			= "CREATE TABLE IF NOT EXISTS gapoktan (" +
								"`id` INTEGER PRIMARY KEY AUTOINCREMENT," +
								"`nama_beras` TEXT NOT NULL," +
								"`jenis_beras` TEXT NOT NULL," +
                                "`tahun_panen` TEXT NOT NULL," +
                                "`harga_panen` TEXT NOT NULL," +
                                "`deskripsi` TEXT NOT NULL," +
                                "`gapoktan` TEXT NOT NULL," +
                                "`kuantitas` TEXT NOT NULL,"+
								"`butir_kepala` TEXT NOT NULL,"+
								"`butir_patah` TEXT NOT NULL,"+
								"`butir_menir` TEXT NOT NULL,"+
								"`butir_kapur` TEXT NOT NULL,"+
								"`butir_kuning` TEXT NOT NULL,"+
								"`hama_penyakit` TEXT NOT NULL,"+
								"`dedak_bekatul` TEXT NOT NULL"+
								")";
		Log.d("oncreate :",sql);
		db.execSQL(sql);
		cursor = db.rawQuery("SELECT * FROM gapoktan",null);
		// mengecek jika table masih kosong
		if(cursor.getCount()<1) {
			dataInsert(db);
		}


	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {


	}

	public void dataInsert(SQLiteDatabase db) {
		// melakukan isian data awal
		String insertSQL = "INSERT INTO gapoktan ( `nama_beras`,`jenis_beras`,`tahun_panen`,`harga_panen`,`deskripsi`,`gapoktan`,`kuantitas`, `butir_kepala`, `butir_patah`, `butir_menir`, `butir_kapur`, `butir_kuning`, `hama_penyakit`, `dedak_bekatul`)" +
				"VALUES ('Cap Jago Putih','Beras Putih','2019','12000','Beras yang diproduksi didaerah jawa tengah','Tani Klaten Tengah','120', '5', '4', '5', '3', '3', '5', '4')," +
				"('Cap Enak 2','Beras Putih','2018','10500','Beras yang diproduksi didaerah jawa tengah','Tani Klaten Tenggara','110', '5', '5', '5', '5', '3', '5', '5')," +
				"('Cap Enak 3','Beras Merah','2018','10500','Beras yang diproduksi didaerah jawa tengah','Tani Klaten Tenggara','90', '5', '3', '3', '5', '3', '4', '5')," +
				"('Cap Enak 4','Beras Merah','2019','11000','Beras yang diproduksi didaerah jawa tengah','Tani Klaten Tenggara','260', '3', '4', '5', '4', '3', '5', '5')," +
				"('Cap Enak 5','Beras Merah','2019','11000','Beras yang diproduksi didaerah jawa tengah','Tani Klaten Tenggara','260', '3', '4', '5', '5', '3', '5', '5')," +
				"('Cap Enak 6','Beras Hitam','2019','12000','Beras yang diproduksi didaerah jawa tengah','Tani Klaten Tenggara','70', '5', '4', '5', '3', '3', '5', '5')," +
				"('Cap Enak 7','Beras Hitam','2019','13000','Beras yang diproduksi didaerah jawa tengah','Tani Klaten Tenggara','190', '5', '4', '5', '5', '3', '5', '5')," +
				"('Cap Jago Kluruk','Beras Putih','2018','10500','Beras yang diproduksi didaerah jawa tengah','Tani Klaten Utara','300', '5', '3', '4', '5', '3', '1', '5')," +
				"('Cap Enak','Beras Putih','2019','12500','Beras yang diproduksi didaerah jawa tengah','Tani Klaten Selatan','110', '5', '5', '5', '5', '3', '5', '4');";
		db.execSQL(insertSQL);
	}

}
