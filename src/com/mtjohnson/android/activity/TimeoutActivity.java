package com.mtjohnson.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.mtjohnson.R;

public class TimeoutActivity extends Activity {
    private Handler updateHandler = new Handler();
    private Integer minutes;
    private Integer seconds;
    private TextView timeoutCountdown;
    private Button pause;
    private Button restart;
    private TimeoutActivity.TimeoutCounter timeoutCounter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeout);

        timeoutCountdown = (TextView) findViewById(R.id.timeoutCountdown);
        restart = (Button) findViewById(R.id.restart);
        pause = (Button) findViewById(R.id.pause);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seconds = minutes * 60;
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pause.getText().equals("Pause")) {
                    pause.setText("Start");
                    updateHandler.removeCallbacks(timeoutCounter);
                } else {
                    pause.setText("Pause");
                    updateHandler.postDelayed(timeoutCounter, 100);
                }
            }
        });

        minutes = (Integer) getIntent().getExtras().get("minutes");
        seconds = minutes * 60;
        timeoutCountdown.setText(Math.round(seconds / 60) + ":00");

        timeoutCounter = new TimeoutCounter();
        updateHandler.postDelayed(timeoutCounter, 100);
    }

    private class TimeoutCounter implements Runnable {
        @Override
        public void run() {
            timeoutCountdown.setText(Math.round(seconds / 60) + ":" + ((seconds % 60 < 10) ? "0" : "") + seconds % 60);
            if (--seconds <= 0)
                return;
            updateHandler.postDelayed(this, 1000);
        }
    }
}