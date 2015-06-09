package com.example.administrator.myapplication150512;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 浏览器
 *
 */
public class WebViewApp extends Activity {

	/** webview控件 */
	private WebView webView;

	/** 当前url地址 */
	private String browserUrl = "file:///android_asset/error.html";
	private String browserUrl2 ="https://www.baidu.com/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// getWindow().requestFeature(Window.FEATURE_PROGRESS);// 用title bar做进度条
		// requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);// 用title
		setContentView(R.layout.browser_layout);
		initWebView();
	}

	/**
	 * 初始化webview
	 */
	private void initWebView() {
		// 得到webView的引用
		webView = (WebView) findViewById(R.id.browser_layout_webview);
		// 支持JavaScript
		webView.getSettings().setJavaScriptEnabled(true);
		// 支持缩放
		webView.getSettings().setBuiltInZoomControls(true);
		// 支持保存数据
		webView.getSettings().setSaveFormData(false);

		// 清除缓存
		webView.clearCache(true);
		// 清除历史记录
		webView.clearHistory();
		// 联网载入
//		webView.loadUrl(browserUrl);
		// String baseUrl = "file:///android_asset";
		// webView.loadDataWithBaseURL(baseUrl, browserUrl, "text/html",
		// "utf-8", null);
		// 设置
		webView.setWebViewClient(new WebViewClient() {

			/** 开始载入页面 */
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				setProgressBarIndeterminateVisibility(true);// 设置标题栏的滚动条开始
				browserUrl = url;
				super.onPageStarted(view, url, favicon);
			}

			/** 捕获点击事件 */
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				webView.loadUrl(url);
				return true;
			}

			/** 错误返回 */
			@Override
			public void onReceivedError(WebView view, int errorCode,
										String description, String failingUrl) {
				super.onReceivedError(view, errorCode, description, failingUrl);
			}

			/** 页面载入完毕 */
			@Override
			public void onPageFinished(WebView view, String url) {
				setProgressBarIndeterminateVisibility(false);// 设置标题栏的滚动条停止
				super.onPageFinished(view, url);
			}

		});

		webView.setWebChromeClient(new WebChromeClient() {
			/** 设置进度条 */
			public void onProgressChanged(WebView view, int newProgress) {
				// 设置标题栏的进度条的百分比
				WebViewApp.this.getWindow().setFeatureInt(
						Window.FEATURE_PROGRESS, newProgress * 100);
				super.onProgressChanged(view, newProgress);
			}

			/** 设置标题 */
			public void onReceivedTitle(WebView view, String title) {
				WebViewApp.this.setTitle(title);
				super.onReceivedTitle(view, title);
			}
		});
		webView.addJavascriptInterface(new RemoteInvokeService(WebViewApp.this,
				webView, browserUrl2), "js_invoke");
		webView.loadUrl(browserUrl);
	}

	/**
	 * 捕获返回键
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack())) {
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
