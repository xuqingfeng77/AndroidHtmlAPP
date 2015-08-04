package com.example.administrator.myapplication150512;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Switch;

public class MainAct extends Activity   {
    private static final String LOG_TAG = "WebViewDemo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

  public void btn_OnClick(View view)
  {
      switch(view.getId())
      {
          case R.id.main_btn_test1:
              lanuchIntent(MainActivity.class);
              break;
          case R.id.main_btn_test2:
              lanuchIntent(WebJSActivity.class);

              break;
          case R.id.main_btn_test3:
              lanuchIntent(WebViewApp.class);

              break;
          case R.id.main_btn_test4:
              lanuchIntent(WebViewAppTwo.class);
              break;
          case R.id.main_btn_test5:
              lanuchIntent(SlidingDrawerActivity.class);
              break;

      }
  }
    private void lanuchIntent(Class act)
    {
        Intent intent=new Intent(MainAct.this,act);
        startActivity(intent);

    }

}
