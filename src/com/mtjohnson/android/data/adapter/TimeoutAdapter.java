package com.mtjohnson.android.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.mtjohnson.R;
import com.mtjohnson.android.data.model.Kid;

import java.util.List;

public class TimeoutAdapter extends BaseAdapter {

    private List<Kid> kids;
    private Context context;

    public TimeoutAdapter(Context context, List<Kid> kids) {
        this.context = context;
        this.kids = kids;
    }

    @Override
    public int getCount() {
        return kids.size();
    }

    @Override
    public Object getItem(int i) {
        return kids.get(i);
    }

    @Override
    public long getItemId(int i) {
        return kids.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(context);
            v = vi.inflate(R.layout.row, null);
        }
        Kid kid = kids.get(position);
        if (kid != null) {
            TextView tt = (TextView) v.findViewById(R.id.toptext);
            TextView bt = (TextView) v.findViewById(R.id.bottomtext);
            if (tt != null) {
                tt.setText("Name: " + kid.getName());
            }
            if (bt != null) {
                bt.setText("Minutes: " + kid.getDefaultMinutes());
            }
            View view = v.findViewById(R.id.icon2);
            view.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.out.println("test");
                        }
                    }
            );
        }
        return v;
    }
}
