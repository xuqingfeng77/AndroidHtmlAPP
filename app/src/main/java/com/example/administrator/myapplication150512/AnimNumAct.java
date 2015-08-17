package com.example.administrator.myapplication150512;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication150512.anim.RiseNumberTextView;
import com.example.administrator.myapplication150512.anim.Utils;

/**
 * 模仿余额宝数字增长效果
 */
public class AnimNumAct extends AppCompatActivity {
    private RiseNumberTextView number1, number2, number3, number4, number5;
    private Button start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_num);
        initView();
    }


    private void initView() {
        number1 = (RiseNumberTextView) findViewById(R.id.number1);
        number2 = (RiseNumberTextView) findViewById(R.id.number2);
        number3 = (RiseNumberTextView) findViewById(R.id.number3);
        number4 = (RiseNumberTextView) findViewById(R.id.number4);
        number5 = (RiseNumberTextView) findViewById(R.id.number5);
        start = (Button) findViewById(R.id.start);

        start.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v == start) {
                initData();
            }
        }
    };
    private void initData() {
        start.setText(Utils.format("##0.00").format(Double.parseDouble(9999999.12 +""))); ;

        number1.withNumber(99999999.19f).start();
        number2.withNumber(9999999.09f).start();
        number3.withNumber(999999.56f).start();
        number4.withNumber(10.01f).start();
        number5.withNumber(0.39f).start();
    }
}
