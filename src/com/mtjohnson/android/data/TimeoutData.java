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
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_NAME = "kids";
    public static final String _ID = BaseColumns._ID;
    public static final String NAME = "name";
    public static final String BIRTHDATE = "birthdate";
    public static final String[] COLUMNS = {_ID, NAME, BIRTHDATE};

    private static TimeoutData timeoutData;

    public TimeoutData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static TimeoutData instance(Context context) {
        if (timeoutData == null) {
            timeoutData = new TimeoutData(context);
        }
        return timeoutData;
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insert(String name, Date birthdate) {
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(NAME, name);
            values.put(BIRTHDATE, birthdate.getTime());
            db.insertOrThrow(TABLE_NAME, null, values);
        } finally {
            try {
                db.close();
            } catch (Exception e) {
            }
        }
    }

    public void updateKid(Kid kid) {
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            ContentValues content = new ContentValues();
            content.put(NAME, kid.getName());
            content.put(BIRTHDATE, kid.getBirthDate().getTime());
            db.update(TABLE_NAME, content, "_id = " + kid.getId(), null);
        } finally {
            try {
                db.close();
            } catch (Exception e) {
            }
        }
    }

    public List<Kid> getKids() {
        List<Kid> kids = new ArrayList<Kid>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = getReadableDatabase();
            cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                Kid kid = new Kid();
                kid.setId(cursor.getInt(0));
                kid.setName(cursor.getString(1));
                kid.setBirthDate(new Date(cursor.getLong(2)));
                kids.add(kid);
            }
        } finally {
            try {
                cursor.close();
            } catch (Exception e) {
            }
            try {
                db.close();
            } catch (Exception e) {
            }
        }
        return kids;
    }

    public void deleteKidById(int id) {
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            db.delete(TABLE_NAME, "_id = ?", new String[]{"" + id});
        } finally {
            try {
                db.close();
            } catch (Exception e) {
            }
        }
    }

    public Kid getKidById(int id) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = getReadableDatabase();
            cursor = db.query(TABLE_NAME, null, "_id = ?", new String[]{"" + id}, null, null, null);
            if (cursor.moveToNext()) {
                Kid kid = new Kid();
                kid.setId(cursor.getInt(0));
                kid.setName(cursor.getString(1));
                kid.setBirthDate(new Date(cursor.getLong(2)));
                return kid;
            }
        } finally {
            try {
                cursor.close();
            } catch (Exception e) {
            }
            try {
                db.close();
            } catch (Exception e) {
            }
        }
        return null;
    }
}
