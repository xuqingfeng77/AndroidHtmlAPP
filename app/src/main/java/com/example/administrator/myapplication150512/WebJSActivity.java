package com.example.administrator.myapplication150512;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class WebJSActivity extends Activity {
    public String fileFullName;//照相后的照片的全整路径
    private WebView myWebView = null;
    private Button myButton = null;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_js);

        myWebView = (WebView) findViewById(R.id.myWebView);

        // 得到设置属性的对象
        WebSettings webSettings = myWebView.getSettings();

        // 使能JavaScript
        webSettings.setJavaScriptEnabled(true);

        // 支持中文，否则页面中中文显示乱码
        webSettings.setDefaultTextEncodingName("UTF-8");//根据项目的编码格式去设置

        // 限制在WebView中打开网页，而不用默认浏览器
        myWebView.setWebViewClient(new WebViewClient());

        // 如果不设置这个，JS代码中的按钮会显示，但是按下去却不弹出对话框
        // Sets the chrome handler. This is an implementation of WebChromeClient
        // for use in handling JavaScript dialogs, favicons, titles, and the
        // progress. This will replace the current handler.
        myWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                // TODO Auto-generated method stub
                return super.onJsAlert(view, url, message, result);
            }


        });

        // 用JavaScript调用Android函数：
        // 先建立桥梁类，将要调用的Android代码写入桥梁类的public函数
        // 绑定桥梁类和WebView中运行的JavaScript代码
        // 将一个对象起一个别名传入，在JS代码中用这个别名代替这个对象，可以调用这个对象的一些方法
        myWebView.addJavascriptInterface(new WebAppInterface(this),
                "myInterfaceName");

        // 载入页面：本地html资源文件
        myWebView.loadUrl("file:///android_asset/sample.html");

        // 这里用一个Android按钮按下后调用JS中的代码
        myButton = (Button) findViewById(R.id.button1);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 用Android代码调用JavaScript函数：
                myWebView.loadUrl("javascript:myFunction()");

                // 这里实现的效果和在网页中点击第一个按钮的效果一致

            }
        });

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
            Toast.makeText(mContext, "takephoto", Toast.LENGTH_LONG).show();
            takePhoto("testimg" + Math.random() * 1000 + 1 + ".jpg");
        }

    }
    /*
        * 调用摄像头的方法
        */
    public void takePhoto(String filename) {
        System.out.println("----start to take photo2 ----");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_MEDIA_TITLE, "TakePhoto");

        //判断是否有SD卡
        String sdDir = null;
        boolean isSDcardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if(isSDcardExist) {
            sdDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            sdDir = Environment.getRootDirectory().getAbsolutePath();
        }
        //确定相片保存路径
        String targetDir = sdDir + "/" + "webview_camera";
        File file = new File(targetDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        fileFullName = targetDir + "/" + filename;
        System.out.println("----taking photo fileFullName: " + fileFullName);
        //初始化并调用摄像头
        intent.putExtra(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(fileFullName)));
        startActivityForResult(intent, 1);
    }
}
