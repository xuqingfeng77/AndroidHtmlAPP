package com.example.administrator.myapplication150512;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * jquery and java
 *
 */
public class WebViewAppTwo extends Activity {

	/** webview控件 */
	private WebView webView;
	private WebView webView2;


	/** 当前url地址 */
	private String browserUrl = "file:///android_asset/error.html";
	private String browserUrl2 ="file:///android_asset/sample.html";
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.browser2_layout);
		mContext=this;

		initWebView();
		initWebView2();
	}

	private void initWebView2() {
		{
			// 得到webView的引用
			webView2 = (WebView) findViewById(R.id.browser2_layout_webview2);
			// 支持JavaScript
			webView2.getSettings().setJavaScriptEnabled(true);
			// 支持缩放
			webView2.getSettings().setBuiltInZoomControls(true);
			// 支持保存数据
			webView2.getSettings().setSaveFormData(false);
			// 支持中文，否则页面中中文显示乱码
			webView2.getSettings().setDefaultTextEncodingName("UTF-8");//根据项目的编码格式去设置
			// 清除缓存
			webView2.clearCache(true);
			// 清除历史记录
			webView2.clearHistory();
			// 联网载入
//		webView.loadUrl(browserUrl);
			// String baseUrl = "file:///android_asset";
			// webView.loadDataWithBaseURL(baseUrl, browserUrl, "text/html",
			// "utf-8", null);
			// 设置
			webView2.setWebViewClient(new WebViewClient() {

				/** 开始载入页面 */
				@Override
				public void onPageStarted(WebView view, String url, Bitmap favicon) {
					setProgressBarIndeterminateVisibility(true);// 设置标题栏的滚动条开始
					browserUrl = url;
					super.onPageStarted(view, url, favicon);
				}

				/** 捕获点击事件 */
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					webView2.loadUrl(url);
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

			webView2.setWebChromeClient(new WebChromeClient() {
				/** 设置进度条 */
				public void onProgressChanged(WebView view, int newProgress) {
					// 设置标题栏的进度条的百分比
					WebViewAppTwo.this.getWindow().setFeatureInt(
							Window.FEATURE_PROGRESS, newProgress * 100);
					super.onProgressChanged(view, newProgress);
				}

				/** 设置标题 */
				public void onReceivedTitle(WebView view, String title) {
					WebViewAppTwo.this.setTitle(title);
					super.onReceivedTitle(view, title);
				}
				@Override
				public boolean onJsAlert(WebView view, String url, String message,
										 JsResult result)
				{
					// TODO Auto-generated method stub
					return super.onJsAlert(view, url, message, result);
				}

			});
			webView2.addJavascriptInterface(new WebAppInterface(mContext), "myInterfaceName");
			webView2.loadUrl(browserUrl2);
		}
	}
	/**
	 * 自定义的Android代码和JavaScript代码之间的桥梁类
	 *
	 * @author 1
	 */
	public class WebAppInterface {
		Context mContext;

		/**
		 * Instantiate the interface and set the context
		 */
		WebAppInterface(Context c) {
			mContext = c;
		}

		/**
		 * Show a toast from the web page
		 */
		// 如果target 大于等于API 17，则需要加上如下注解
		@JavascriptInterface
		public void showToast(String toast) {
			// Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
			Toast.makeText(mContext, toast, Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * 初始化webview
	 */
	private void initWebView() {
		// 得到webView的引用
		webView = (WebView) findViewById(R.id.browser2_layout_webview);
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
				WebViewAppTwo.this.getWindow().setFeatureInt(
						Window.FEATURE_PROGRESS, newProgress * 100);
				super.onProgressChanged(view, newProgress);
			}

			/** 设置标题 */
			public void onReceivedTitle(WebView view, String title) {
				WebViewAppTwo.this.setTitle(title);
				super.onReceivedTitle(view, title);
			}
			@Override
			public boolean onJsAlert(WebView view, String url, String message,
									 JsResult result)
			{
				// TODO Auto-generated method stub
				return super.onJsAlert(view, url, message, result);
			}

		});
		webView.addJavascriptInterface(new RemoteInvokeService(WebViewAppTwo.this,
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
