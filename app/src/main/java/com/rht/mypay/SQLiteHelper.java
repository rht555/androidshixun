package com.rht.mypay;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabase;

    public SQLiteHelper(Context context) {
        super(context, DButils.DATABASE_NAME, null, DButils.DATABASE_VERSION);
        sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DButils.DATABASE_TABLE + "(" + DButils.NOTE_ID + " integer primary key autoincrement," + DButils.NOTE_MONEY + " test," + DButils.NOTE_TYPE + " text," + DButils.NOTE_REMARKS + " text," + DButils.NOTE_TIME + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData(String money, String moneyType, String moneyRemarks, String moneyTime) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DButils.NOTE_MONEY, money);
        contentValues.put(DButils.NOTE_TYPE, moneyType);
        contentValues.put(DButils.NOTE_REMARKS, moneyRemarks);
        contentValues.put(DButils.NOTE_TIME, moneyTime);
        boolean b = sqLiteDatabase.insert(DButils.DATABASE_TABLE, null, contentValues) > 0;
        return b;
    }

    public boolean deleteData(String id) {
        String sql = DButils.NOTE_ID + "=?";
        String[] contentValuesArray = new String[]{String.valueOf(id)};
        return sqLiteDatabase.delete(DButils.DATABASE_TABLE, sql, contentValuesArray) > 0;
    }

    public boolean updateData(String id, String newMoney,   String newRemarks) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DButils.NOTE_MONEY, newMoney);
        contentValues.put(DButils.NOTE_REMARKS, newRemarks);
        String sql = DButils.NOTE_ID + "=?";
        String[] strings = new String[]{id};
        return sqLiteDatabase.update(DButils.DATABASE_TABLE, contentValues, sql, strings) > 0;
    }

    public List<moneyList> query() {
        List<moneyList> lists = new ArrayList<moneyList>();
        Cursor cursor = sqLiteDatabase.query(DButils.DATABASE_TABLE, null, null, null, null, null, DButils.NOTE_ID + " desc");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                moneyList money = new moneyList();
                String id = String.valueOf(cursor.getInt(cursor.getColumnIndex(DButils.NOTE_ID)));
                String moneypad = cursor.getString(cursor.getColumnIndex(DButils.NOTE_MONEY));
                String timepad = cursor.getString(cursor.getColumnIndex(DButils.NOTE_TIME));
                String typepad = cursor.getString(cursor.getColumnIndex(DButils.NOTE_TYPE));
                String remarks = cursor.getString(cursor.getColumnIndex(DButils.NOTE_REMARKS));
                money.setId(id);
                money.setMoney(moneypad);
                money.setDay(timepad);
                money.setRemarks(remarks);
                money.setType(typepad);
                lists.add(money);
            }
            cursor.close();
        }
        return lists;
    }
}
