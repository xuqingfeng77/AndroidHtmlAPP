package com.example.administrator.myapplication150512;

import android.app.Activity;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

public class RemoteInvokeService{
	private Activity context;
	private String url;
	private WebView webView;
	private String browserUrl2 ="https://www.baidu.com/";

	public RemoteInvokeService(Activity paramActivity, WebView paramWebView,
			String paramString1) {
		this.context = paramActivity;
		this.webView = paramWebView;
		this.url = paramString1;
	}
	// 如果target 大于等于API 17，则需要加上如下注解
	@JavascriptInterface

	public void goNetSetting() {
		this.context.startActivityForResult(new Intent(
				"android.settings.WIRELESS_SETTINGS"), 0);
	}
	// 如果target 大于等于API 17，则需要加上如下注解
	@JavascriptInterface
	public void reload() {
		Toast.makeText(context, "回调打开网页成功", Toast.LENGTH_SHORT).show();
		this.webView.loadUrl(browserUrl2);
	}

}