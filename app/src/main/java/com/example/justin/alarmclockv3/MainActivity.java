package com.example.justin.alarmclockv3;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    //Variables for alarm
    long currentTime = Calendar.getInstance().getTimeInMillis();
    MediaPlayer mediaSong;
    int startId = 0;
    long DinnerTime;
    boolean isRunning;
//    Timer timer = new Timer();


    //Starts the alarm by calling the sound file, sets the startId to 1
    public void StartAlarm(){
        Toast.makeText(getApplicationContext(), "I am running!", Toast.LENGTH_SHORT).show();
        mediaSong = MediaPlayer.create(this, R.raw.tinkerbell);
        mediaSong.start();
        startId = 1;
    }

    //Stops the alarm from running and resets the startId
    public void StopRunningAlarm(){
        mediaSong.stop();
        startId = 0;
    }

//  function for setting the dinner time.
    public long DinnerTime (){

        long dTime;

        Calendar c= Calendar.getInstance();
        c.add(Calendar.HOUR, 2);
        c.add(Calendar.MINUTE, 19);
        c.add(Calendar.SECOND, 0);
        c.add(Calendar.MILLISECOND, 0);
        c.add(Calendar.AM_PM, Calendar.PM);
        dTime = c.getTimeInMillis();

        return dTime;
    }

    //On create the program runs this code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DinnerTime = DinnerTime();

        if(DinnerTime == currentTime){
            Toast.makeText(getApplicationContext(), "running!", Toast.LENGTH_SHORT).show();
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(1000);
            StartAlarm();
        }

        //Listener for Stop Button
        final Button StopAlarm = (Button) findViewById(R.id.endAlarm);

        StopAlarm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(startId == 1) {
                    StopRunningAlarm();
                }else{
                    Toast.makeText(getApplicationContext(), "Alarm already off", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}
