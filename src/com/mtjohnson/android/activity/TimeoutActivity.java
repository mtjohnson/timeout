package com.mtjohnson.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.mtjohnson.R;
import com.mtjohnson.android.data.TimeoutData;
import com.mtjohnson.android.data.adapter.TimeoutAdapter;

public class TimeoutActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TimeoutData timeoutData = new TimeoutData(this);
        timeoutData.getKids();
        ListView listView = (ListView) findViewById(R.id.names);
        listView.setAdapter(new TimeoutAdapter(this, timeoutData.getKids()));

        /*ImageView imageView = (ImageView) findViewById(R.id.icon2);
        imageView.setClickable(true);
        imageView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println("test");
                    }
                }
        );*/
    }
}
