package com.td.storecalc;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import com.td.storecalc.*;
import java.sql.*;
import java.sql.SQLException;
import android.util.*;
import java.util.*;
import Order;

public class OrderDataSource
 {
    private SQLiteDatabase database;
    private OrderDatabaseHelper dbHelper;
	private Context context;

    private String[] allColumns = { OrderDatabaseHelper.COLUMN_ID, OrderDatabaseHelper.COLUMN_ITEM_NAME, OrderDatabaseHelper.COLUMN_ITEM_PRICE, OrderDatabaseHelper.COLUMN_ITEM_QUANTITY, OrderDatabaseHelper.COLUMN_ITEM_TOTAL_PRICE };
	
	
    public OrderDataSource(Context context, String databaseName) {
		this.context = context;
		dbHelper = new OrderDatabaseHelper(context, databaseName);
	}

    public void open() {
		database = dbHelper.getWritableDatabase();
	}
	
    public void close() {
		if (database != null) {
			database.close();
			database = null;
		}
	}
	
	
	//Method for Plus Button 
    public void createItem(String itemName, double itemPrice) {
		// Check if item already exists in database
		Cursor cursor = database.rawQuery("SELECT * FROM " + OrderDatabaseHelper.TABLE_ORDER + " WHERE " + OrderDatabaseHelper.COLUMN_ITEM_NAME + "=?", new String[]{itemName});
		if (cursor.getCount() > 0) {
			// Item already exists, update quantity by plus one
			cursor.moveToFirst();
			int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(OrderDatabaseHelper.COLUMN_ID));
			int oldQuantity = cursor.getInt(cursor.getColumnIndexOrThrow(OrderDatabaseHelper.COLUMN_ITEM_QUANTITY));
			int newQuantity = oldQuantity + 1;
			double newTotalPrice = itemPrice * newQuantity;
			ContentValues values = new ContentValues();
			values.put(OrderDatabaseHelper.COLUMN_ITEM_QUANTITY, newQuantity);
			values.put(OrderDatabaseHelper.COLUMN_ITEM_TOTAL_PRICE, newTotalPrice);
			database.update(OrderDatabaseHelper.TABLE_ORDER, values, OrderDatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(itemId)});
		} else {
			// Item doesn't exist, insert new item into database
			int itemQuantity = 1;
			double itemTotalPrice = itemPrice * itemQuantity;
			ContentValues values = new ContentValues();
			values.put(OrderDatabaseHelper.COLUMN_ITEM_NAME, itemName);
			values.put(OrderDatabaseHelper.COLUMN_ITEM_PRICE, itemPrice);
			values.put(OrderDatabaseHelper.COLUMN_ITEM_QUANTITY, itemQuantity);
			values.put(OrderDatabaseHelper.COLUMN_ITEM_TOTAL_PRICE, itemTotalPrice);
			database.insert(OrderDatabaseHelper.TABLE_ORDER, null, values);
		}
		cursor.close();
	}
	
	//Method for Minus Button
	public void deleteOrDecrementItem(String itemName) {
		// Check if item exists in database
		Cursor cursor = database.rawQuery("SELECT * FROM " + OrderDatabaseHelper.TABLE_ORDER + " WHERE " + OrderDatabaseHelper.COLUMN_ITEM_NAME + "=?", new String[]{itemName});
		if (cursor.getCount() > 0) {
			// Item exists in database
			cursor.moveToFirst();
			int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(OrderDatabaseHelper.COLUMN_ID));
			int oldQuantity = cursor.getInt(cursor.getColumnIndexOrThrow(OrderDatabaseHelper.COLUMN_ITEM_QUANTITY));

			if (oldQuantity == 1) {
				// Item quantity is 1, delete the item
				database.delete(OrderDatabaseHelper.TABLE_ORDER, OrderDatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(itemId)});
			} else {
				// Item quantity is greater than 1, decrement the quantity by 1
				int newQuantity = oldQuantity - 1;
				double itemPrice = cursor.getDouble(cursor.getColumnIndexOrThrow(OrderDatabaseHelper.COLUMN_ITEM_PRICE));
				double newTotalPrice = itemPrice * newQuantity;
				ContentValues values = new ContentValues();
				values.put(OrderDatabaseHelper.COLUMN_ITEM_QUANTITY, newQuantity);
				values.put(OrderDatabaseHelper.COLUMN_ITEM_TOTAL_PRICE, newTotalPrice);
				database.update(OrderDatabaseHelper.TABLE_ORDER, values, OrderDatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(itemId)});
			}
		}
		cursor.close();
	}
	
	public void closeDatabase() {
		if (database != null && database.isOpen()) {
			database.close();
		}
	}
	
	public int getItemQuantity(String itemName) {
		int quantity = 0;
		Cursor cursor = database.rawQuery("SELECT " + OrderDatabaseHelper.COLUMN_ITEM_QUANTITY + " FROM " + OrderDatabaseHelper.TABLE_ORDER + " WHERE " + OrderDatabaseHelper.COLUMN_ITEM_NAME + "=?", new String[]{itemName});
		if (cursor.moveToFirst()) {
			quantity = cursor.getInt(cursor.getColumnIndexOrThrow(OrderDatabaseHelper.COLUMN_ITEM_QUANTITY));
		}
		cursor.close();
		return quantity;
	}
	
    public double getOrderTotal() {
        Cursor cursor = database.rawQuery("SELECT SUM(" + OrderDatabaseHelper.COLUMN_ITEM_TOTAL_PRICE + ") FROM " + OrderDatabaseHelper.TABLE_ORDER, null);
        cursor.moveToFirst();
        double orderTotal = cursor.getDouble(0);
        cursor.close();
        return orderTotal;
    }
	public void clearDatabase() {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		try {
			db.delete(OrderDatabaseHelper.TABLE_ORDER, null, null);
		} catch (Exception e) {
			Log.e("ClearDatabase", "Error deleting items: " + e.getMessage());
		} finally {
			db.close();
		}
	}
	public List<Order> getAllItems() {
		final List<Order> itemList = new ArrayList<>();

		// Execute the query to get all items from the database
		final Cursor cursor = database.query(
            OrderDatabaseHelper.TABLE_ORDER,
            allColumns,
            null,
            null,
            null,
            null,
            null
		);

		// Iterate through the cursor and create an Order object for each row
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			final Order order = new Order(
                cursor.getInt(cursor.getColumnIndexOrThrow(OrderDatabaseHelper.COLUMN_ID)),
                "",
                cursor.getString(cursor.getColumnIndexOrThrow(OrderDatabaseHelper.COLUMN_ITEM_NAME)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(OrderDatabaseHelper.COLUMN_ITEM_PRICE)),
                cursor.getInt(cursor.getColumnIndexOrThrow(OrderDatabaseHelper.COLUMN_ITEM_QUANTITY)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(OrderDatabaseHelper.COLUMN_ITEM_TOTAL_PRICE)),
                0.0
			);
			itemList.add(order);
			cursor.moveToNext();
		}
		cursor.close();

		return itemList;
	}
	//Use for loading a different database
	public void changeDatabase(String newDatabaseName) {
		close();
		dbHelper = new OrderDatabaseHelper(context, newDatabaseName);
		open();
	}
}
