package com.td.storecalc;
import android.app.Activity;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;
import android.content.Intent;
import android.content.pm.*;
public class SplashScreen extends Activity { 
	private TimerTask tim;
	private Timer timer = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.splash_screen);
//        getActionBar().hide();
        tim = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(getApplication(),LoadCreate.class); 
                            startActivity(intent);
                            finish();}});}};
        timer.schedule(tim, (3000));}} 
