package com.perjuangan.smartfarm.CRUD;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.perjuangan.smartfarm.R;

public class InputPenjual extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_penjual);

		WebView webView = findViewById(R.id.webview);
		webView.getSettings().setLoadsImagesAutomatically(true);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setDomStorageEnabled(true);

		// Tiga baris di bawah ini agar laman yang dimuat dapat
		// melakukan zoom.
		webView.getSettings().setSupportZoom(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.getSettings().setDisplayZoomControls(false);
		// Baris di bawah untuk menambahkan scrollbar di dalam WebView-nya
		webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		webView.setWebViewClient(new WebViewClient());
		webView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSc6maoEyc6EhGCOF9_NncsEle2LUCbnpO3Da5hyoQ-R9OENsA/viewform");
	}
}
