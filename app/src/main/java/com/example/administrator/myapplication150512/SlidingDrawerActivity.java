package com.example.administrator.myapplication150512;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.SlidingDrawer;
import android.widget.Toast;

import com.example.administrator.myapplication150512.com.test.app.TempSlidingDrawer;


public class SlidingDrawerActivity extends AppCompatActivity {

    private Button btn_drawer;//抽屉的按钮
    private TempSlidingDrawer mDrawer;
    private Boolean flag = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_drawer);

        initDrawer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void initDrawer() {
        btn_drawer = (Button) findViewById(R.id.handle);
        mDrawer = (TempSlidingDrawer) findViewById(R.id.slidingDrawer1);
        Button mbtn=(Button)findViewById(R.id.btn1);
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SlidingDrawerActivity.this, "ok  454545454", Toast.LENGTH_LONG).show();
            }
        });
        mDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {

            public void onDrawerOpened() {

                flag = true;
                btn_drawer.setBackgroundResource(R.mipmap.down);
            }
        });

        mDrawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {

            public void onDrawerClosed() {

                flag = false;
                btn_drawer.setBackgroundResource(R.mipmap.up);
            }
        });

        mDrawer.setOnDrawerScrollListener(new SlidingDrawer.OnDrawerScrollListener() {

            public void onScrollStarted() {

            }

            public void onScrollEnded() {

            }
        });
    }

    public void btn_OnClick(View view) {
        switch (view.getId()) {
            case R.id.drawer_btn:
                Toast.makeText(this, "ok  hahh", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
