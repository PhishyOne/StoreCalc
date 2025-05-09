package com.td.storecalc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OrderDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_ORDER = "order_table";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ITEM_NAME = "item_name";
    public static final String COLUMN_ITEM_PRICE = "item_price";
    public static final String COLUMN_ITEM_QUANTITY = "item_quantity";
    public static final String COLUMN_ITEM_TOTAL_PRICE = "item_total_price";
    public static final String COLUMN_ORDER_TOTAL_PRICE = "order_total_price";

    private String DATABASE_NAME;

    public OrderDatabaseHelper(Context context, String databaseName) {
        super(context, databaseName + ".db", null, DATABASE_VERSION);
        DATABASE_NAME = databaseName;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ORDER_TABLE = "CREATE TABLE " + TABLE_ORDER + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_ITEM_NAME + " TEXT,"
            + COLUMN_ITEM_PRICE + " REAL,"
            + COLUMN_ITEM_QUANTITY + " INTEGER,"
            + COLUMN_ITEM_TOTAL_PRICE + " REAL,"
            + COLUMN_ORDER_TOTAL_PRICE + " REAL"
            + ")";
        db.execSQL(CREATE_ORDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
        onCreate(db);
    }
}

