package com.perjuangan.smartfarm.Pembeli;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.perjuangan.smartfarm.R;

public class Deskripsi extends AppCompatActivity {
    TextView isi, title;
    ImageView gambar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskripsi);

        isi = (TextView)findViewById(R.id.isi_des);
        title   = (TextView)findViewById(R.id.title_des);
        gambar  = (ImageView)findViewById(R.id.gambar);

        String jenis    = getIntent().getStringExtra("jenis");

        // isi manual dibawah untuk deskripsi tiap beras
        // jenis ada 3, putih, merah, hitam
        if(jenis.equals("putih")) {
            isi.setText("Beras putih adalah beras yang biasa ditemui disekeliling kita");
            title.setText("Beras Putih");
            gambar.setBackgroundResource(R.drawable.putih);
        } else if(jenis.equals("merah")) {
            isi.setText("Beras adalah makanan yang biasa ditemui disekeliling kita");
            title.setText("Beras Merah");
            gambar.setBackgroundResource(R.drawable.merah);
        } else if(jenis.equals("hitam")) {
            isi.setText("Beras Hitam adalah makanan yang biasa ditemui disekeliling kita");
            title.setText("Beras Hitam");
            gambar.setBackgroundResource(R.drawable.hitam);
        }


    }
}
