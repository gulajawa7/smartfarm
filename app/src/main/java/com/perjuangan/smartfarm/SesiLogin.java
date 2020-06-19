package com.perjuangan.smartfarm;

import android.content.Context;
import android.content.SharedPreferences;

public class SesiLogin {
    private final String LOGIN_STATUS   = "login_status";
    private final String LOGIN_EMAIL    = "login_email";

    private SharedPreferences Prefer;
    private Context context;

    public SesiLogin (Context context) {
        Prefer = context.getSharedPreferences("shared",
                Context.MODE_PRIVATE);
        this.context = context;
    }

    public void putIsLogin(boolean statuslogin) {
        SharedPreferences.Editor edit = Prefer.edit();
        edit.putBoolean(LOGIN_STATUS, statuslogin);
        edit.commit();
    }

    public boolean getIsLogin() {
        return Prefer.getBoolean(LOGIN_STATUS, false);
    }

    public void putEMail(String email) {
        SharedPreferences.Editor edit = Prefer.edit();
        edit.putString(LOGIN_EMAIL, email);
        edit.commit();
    }

    public String getEmail() {
        return Prefer.getString(LOGIN_EMAIL,"");
    }
}
