package com.perjuangan.smartfarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.perjuangan.smartfarm.CRUD.Input;
import com.perjuangan.smartfarm.CRUD.InputPenjual;

public class MenuInput extends AppCompatActivity {
	Button btn_penjual, btn_beras;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_input);

		btn_beras = findViewById(R.id.btn_input_beras);
		btn_beras.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent input_beras = new Intent(MenuInput.this, Input.class);
				startActivity(input_beras);
			}
		});
		btn_penjual = findViewById(R.id.btn_input_penjual);
		btn_penjual.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent input_penjual = new Intent(MenuInput.this, InputPenjual.class);
				startActivity(input_penjual);
			}
		});
	}
}
