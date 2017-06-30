package com.example.minhl.alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    private Button btn;
    private EditText edtHour;
    private EditText edtMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtHour = (EditText) findViewById(R.id.edt_hour);
        edtMinute = (EditText) findViewById(R.id.edt_minute);
        btn = (Button) findViewById(R.id.btn);
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
    }

    public void alarmClock(View view) {
        String temp1 = edtHour.getText().toString();
        String temp2 = edtMinute.getText().toString();
        if ((temp1.isEmpty()) || (temp2.isEmpty())) {
            Toast.makeText(this, "Chưa nhập dữ liêu ", Toast.LENGTH_SHORT).show();
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(temp1));
        calendar.set(Calendar.MINUTE, Integer.parseInt(temp2));
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }
}
