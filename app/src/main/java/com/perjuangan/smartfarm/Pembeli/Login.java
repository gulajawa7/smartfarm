package com.perjuangan.smartfarm.Pembeli;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.perjuangan.smartfarm.Admin.MainActivity;
import com.perjuangan.smartfarm.CRUD.InputPenjual;
import com.perjuangan.smartfarm.R;
import com.perjuangan.smartfarm.SesiLogin;

public class Login extends Fragment {
	EditText edt_email, edt_password;
	TextView txt_email, txt_password,
			txt_login, txtregisterpenjual;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_login, container, false);

		return view;
	}

	// Method ini dipanggil sesaat setelah onCreateView().
	// Semua pembacaan view dan penambahan listener dilakukan disini (atau // bisa juga didalam onCreateView)
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

        txt_email = view.findViewById(R.id.txt_login_email);
        txt_password = view.findViewById(R.id.txt_login_password);

        edt_email = view.findViewById(R.id.edt_login_email);
        edt_password = view.findViewById(R.id.edt_login_password);

        txt_login = view.findViewById(R.id.fitur_login);
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SesiLogin sesi = new SesiLogin(getContext());
                String usernameKey = edt_email.getText().toString();
                String passwordKey = edt_password.getText().toString();

                if (usernameKey.equals("admin@gmail.com") && passwordKey.equals("admin123")) {
                    //jika login berhasil langsung membuat sesi dan meredirect activity
                    sesi.putIsLogin(true);
                    Log.d("sesilogin", String.valueOf(sesi.getIsLogin()));
                    Intent login = new Intent(getActivity(), MainActivity.class);
                    startActivity(login);
                    getActivity().finish();
                } else {
                    //Jika login gagal
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Username atau Password Anda Salah!")
                            .setNegativeButton("Retry", null).create().show();
                }
            }
        });

        txtregisterpenjual  = view.findViewById(R.id.txt_register_penjual);
        txtregisterpenjual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(getActivity(), InputPenjual.class);
                startActivity(register);
            }
        });
    }
}
