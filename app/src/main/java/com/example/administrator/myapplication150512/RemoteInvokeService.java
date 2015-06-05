package com.example.administrator.myapplication150512;

import android.app.Activity;
import android.content.Intent;
import android.webkit.WebView;

public class RemoteInvokeService {
	private Activity context;
	private String url;
	private WebView webView;

	public RemoteInvokeService(Activity paramActivity, WebView paramWebView,
			String paramString1) {
		this.context = paramActivity;
		this.webView = paramWebView;
		this.url = paramString1;
	}

	public void goNetSetting() {
		this.context.startActivityForResult(new Intent(
				"android.settings.WIRELESS_SETTINGS"), 0);
	}

	public void reload() {
		this.webView.loadUrl(this.url);
	}
}