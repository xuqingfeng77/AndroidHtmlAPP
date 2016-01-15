package com.example.administrator.myapplication150512;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication150512.anim.RiseNumberTextView;
import com.example.administrator.myapplication150512.anim.Utils;

import java.util.Calendar;
import java.util.Date;

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
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        System.out.println(year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second);

        Date date2=new Date(year,month,date,hour,minute,second);
        Date date1 = new Date(2013,6,10,0,0,01);
        int days=daysBetween(date1,date2);
        start.setText("相识"+days+"天"); ;

        number1.withNumber(99999999.19f).start();
        number2.withNumber(9999999.09f).start();
        number3.withNumber(999999.56f).start();
        number4.withNumber(10.01f).start();
        number5.withNumber(0.39f).start();
    }
    /**
     * 计算两个日期之间相差的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int daysBetween(Date date1,Date date2)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }
}
