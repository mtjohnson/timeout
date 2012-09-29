package com.mtjohnson.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.mtjohnson.R;
import com.mtjohnson.android.data.TimeoutData;

public class TimeoutActivity extends Activity {
    private static final String TIMEOUT_PREFERENCES = "timout_shared_prefs";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TimeoutData timeoutData = new TimeoutData(this);
        timeoutData.getKids();
        ListView listView = (ListView) findViewById(R.id.names);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"test1", "test2"}));
    }
}
