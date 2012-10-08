package com.mtjohnson.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.mtjohnson.R;
import com.mtjohnson.android.data.TimeoutData;
import com.mtjohnson.android.data.adapter.TimeoutAdapter;

public class TimeoutListActivity extends Activity {
    private final TimeoutData timeoutData = TimeoutData.instance(this);
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listView = (ListView) findViewById(R.id.names);
        listView.setAdapter(new TimeoutAdapter(this, timeoutData.getKids()));
    }

    @Override
    protected void onResume() {
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
        ListView listView = (ListView) findViewById(R.id.names);
        listView.setAdapter(new TimeoutAdapter(this, timeoutData.getKids()));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public boolean hasWindowFocus() {
        return super.hasWindowFocus();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemAdd:
                this.startActivity(new Intent(this, AddKidActivity.class));
                break;
        }
        return true;
    }
}
