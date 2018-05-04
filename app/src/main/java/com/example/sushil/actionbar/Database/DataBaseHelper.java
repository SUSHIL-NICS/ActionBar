package com.example.sushil.actionbar.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sushil.actionbar.ActionbarDto1;

import java.util.ArrayList;

/**
 * Created by sushil on 30-07-2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "ActionBar";
    public final static int DATBASE_VERSION = 1;
    public SQLiteDatabase database;

    public static final String IMAGE_TABLE = "history";
    public static final String IMAGE_TABLE_ID = "_id";
    public static final String IMAGE_TABLE_NAME = "name";
    public static final String IMAGE_TABLE_IMAGE= "image";

    private final static String ORDER_HISTORY_QUERY = "create table " + IMAGE_TABLE + "(" + IMAGE_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + IMAGE_TABLE_NAME + " blob not null,"
            + IMAGE_TABLE_IMAGE + " TEXT)";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL(ORDER_HISTORY_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertHistory(ActionbarDto1 ohd)
    {
        database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(IMAGE_TABLE_IMAGE,ohd.getImage());
        contentValues.put(IMAGE_TABLE_NAME,ohd.getCountry());
        database.insert(IMAGE_TABLE,null,contentValues);
        close();
    }

    public ArrayList<ActionbarDto1> getCartItemFavorite() {
        database = getReadableDatabase();
        ArrayList<ActionbarDto1> itemList = new ArrayList<ActionbarDto1>();
        Cursor c = database.rawQuery("select * from " + IMAGE_TABLE, null);
        if (c.getCount() != 0) {
            c.moveToFirst();
            do {
                ActionbarDto1 d = new ActionbarDto1();
                String favorite=c.getString(c.getColumnIndex(IMAGE_TABLE_NAME));
                d.setCountry(c.getString(c.getColumnIndex(IMAGE_TABLE_NAME)));
                d.setImage(c.getInt(c.getColumnIndex(IMAGE_TABLE_IMAGE)));
                if(favorite!=null && !favorite.equalsIgnoreCase("0"))
                    itemList.add(d);
            } while (c.moveToNext());
        }
        close();
        return itemList;
    }
}
