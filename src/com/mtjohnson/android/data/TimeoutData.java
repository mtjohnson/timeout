package com.mtjohnson.android.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import com.mtjohnson.android.data.model.Kid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeoutData extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "timeout.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "kids";
    public static final String _ID = BaseColumns._ID;
    public static final String NAME = "name";
    public static final String BIRTHDATE = "birthdate";
    public static final String[] COLUMNS = {_ID, NAME, BIRTHDATE};

    public TimeoutData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                "CREATE TABLE " + TABLE_NAME + " ("
                        + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + NAME + " TEXT NOT NULL, "
                        + BIRTHDATE + " INTEGER"
                        + ");";
        db.execSQL(sql);
        db.execSQL("insert into " + TABLE_NAME + "(name, birthdate) values ('testkid', 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insert(String name, Date birthdate) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(BIRTHDATE, birthdate.getTime());
        db.insertOrThrow(TABLE_NAME, null, values);
    }

    public List<Kid> getKids() {
        List<Kid> kids = new ArrayList<Kid>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Kid kid = new Kid();
            kid.setId(cursor.getInt(0));
            kid.setName(cursor.getString(1));
            kid.setBirthDate(new Date(cursor.getInt(2)));
            kids.add(kid);
        }
        return kids;
    }
}
