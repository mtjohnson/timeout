package com.mtjohnson.android.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.mtjohnson.R;
import com.mtjohnson.android.data.TimeoutData;
import com.mtjohnson.android.data.model.Kid;

import java.util.Calendar;
import java.util.Date;

public class EditKidActivity extends Activity {
    private final TimeoutData timeoutData = TimeoutData.instance(this);
    private static final int DATE_PICKER_DIALOG_ID = 1;
    private Button edit;
    private Button cancel;
    private Button delete;
    private EditText nameField;
    private EditText birthdateField;
    private int kidId;
    private Kid kid;
    private Date birthDate;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);

        kidId = this.getIntent().getExtras().getInt("kid_id");

        kid = timeoutData.getKidById(kidId);

        nameField = (EditText) findViewById(R.id.nameField);
        birthdateField = (EditText) findViewById(R.id.birthdateField);
        edit = (Button) findViewById(R.id.editButton);
        cancel = (Button) findViewById(R.id.cancelButton);
        delete = (Button) findViewById(R.id.deleteButton);

        setFields(kid);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateModel();
                timeoutData.updateKid(kid);
                Toast.makeText(getApplicationContext(), nameField.getText().toString() + " edited.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        birthdateField.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                showDialog(DATE_PICKER_DIALOG_ID);
                return true;
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setCancelable(true);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        timeoutData.deleteKidById(kidId);
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setMessage("Are you sure you want to delete this kid?");
                AlertDialog alertDialog = builder.show();
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
            birthdateField.setText(birthDate.toString());
        }
    };

    private void updateModel() {
        if (birthDate != null) {
            kid.setBirthDate(birthDate);
        }
        kid.setName(nameField.getText().toString());
    }

    private void setFields(Kid kid) {
        nameField.setText(kid.getName());
        birthdateField.setText("" + kid.getBirthDate());
        edit.setText("Edit");
    }
}