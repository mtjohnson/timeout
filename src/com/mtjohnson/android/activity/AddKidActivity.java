package com.mtjohnson.android.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.mtjohnson.R;
import com.mtjohnson.android.data.TimeoutData;

import java.util.Calendar;
import java.util.Date;

public class AddKidActivity extends Activity {
    private final TimeoutData timeoutData = TimeoutData.instance(this);
    private static final int DATE_PICKER_DIALOG_ID = 1;
    private Button addButton;
    private Date birthDate;
    private EditText birthdateEdit;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        final EditText nameField = (EditText) findViewById(R.id.nameField);
        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeoutData.insert(nameField.getText().toString(), birthDate);
                Toast.makeText(getApplicationContext(), nameField.getText().toString() + " added.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        birthdateEdit = (EditText) findViewById(R.id.birthdateField);
        birthdateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_PICKER_DIALOG_ID);
            }
        });
        birthdateEdit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                showDialog(DATE_PICKER_DIALOG_ID);
                return true;
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Calendar calendar = Calendar.getInstance();
        switch (id) {
            case DATE_PICKER_DIALOG_ID:
                return new DatePickerDialog(this, datePickerListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, datePicker.getYear());
            calendar.set(Calendar.MONTH, datePicker.getMonth());
            calendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
            birthDate = calendar.getTime();
            birthdateEdit.setText(birthDate.toString());
        }
    };
}