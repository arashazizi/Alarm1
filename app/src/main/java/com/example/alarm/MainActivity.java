package com.example.alarm;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends ActionBarActivity {

    private PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        int i = preferences.getInt("numberOfLaunches", 1);

        if(i<2){
            alarmMethod();
            i++;
            editor.putInt("numberOfLaunches",i);
            editor.apply();
            //editor.commit();
        }

        //if(savedInstanceState == null){ splashMethod(); }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void alarmMethod(){
        Intent myIntent = new Intent(this, NotifyService.class);
        AlarmManager alarmManger = (AlarmManager)getSystemService(ALARM_SERVICE);
        pendingIntent = PendingIntent.getService(this, 0, myIntent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,55);
        calendar.set(Calendar.HOUR,4);
        calendar.set(Calendar.AM_PM,Calendar.PM);
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        alarmManger.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),1000*3600*24,pendingIntent);


       Toast.makeText(MainActivity.this, "Notification Is Set", Toast.LENGTH_LONG).show();


    }



}
