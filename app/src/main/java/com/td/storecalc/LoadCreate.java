package com.td.storecalc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.SharedPreferences;
import java.util.List;
import java.util.ArrayList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.CompoundButton;
import android.preference.PreferenceManager;
import android.widget.TextView;
import Order;

public class LoadCreate extends AppCompatActivity {
	String[] databaseNames;
	String databaseName;

			@Override
			protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
				setContentView(R.layout.load_create);
				// Get a List of Saved Orders
				String[] allDatabaseNames = this.databaseList();
				List<String> filteredDatabaseNames = new ArrayList<>();
				for (String dbName : allDatabaseNames) {
					if (!dbName.contains("-journal")) {
						filteredDatabaseNames.add(dbName.replace(".db", ""));
					}
				}
				databaseNames = filteredDatabaseNames.toArray(new String[0]);
        //Put the List into the ListView
        final ListView listView = findViewById(R.id.listLoad);
		final TextView loadOrder = findViewById(R.id.txtLoad);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, databaseNames);
        listView.setAdapter(adapter);
		//If there are no saved orders, hide Load Order textview
		if(databaseNames.length == 0){	loadOrder.setVisibility(View.GONE);}
        //Listen for Order Name Click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					databaseName = (String) parent.getItemAtPosition(position);
					AlertDialog.Builder builder = new AlertDialog.Builder(LoadCreate.this);
					builder.setTitle("Edit or Use " + databaseName + "?");
					builder.setMessage("Would You Like to Edit " + databaseName + " or Use its Contents for a New Order?");
					// Load Chosen Order
					builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								startStoreCalc(databaseName);}});
					// Use the Chosen Order as Template
					builder.setPositiveButton("Use", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								AlertDialog.Builder newBuilder = new AlertDialog.Builder(LoadCreate.this);
								newBuilder.setTitle("Enter New Order Name");
								final EditText newInput = new EditText(LoadCreate.this);
								newInput.setFilters(new InputFilter[]{new InputFilter() {
									public CharSequence filter(CharSequence source, int start, int end,
											   Spanned dest, int dstart, int dend) {
										StringBuilder filtered = new StringBuilder();
										for (int i = start; i < end; i++) {
											char character = source.charAt(i);
											if (!Character.isWhitespace(character)) {
												filtered.append(character);}}
												return filtered.toString();}}});
								newBuilder.setView(newInput);
								// OK Button
								newBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface newDialog, int which) {
											String newDatabaseName = newInput.getText().toString().trim();
											if (newDatabaseName.isEmpty()) {
												Toast.makeText(getApplicationContext(), "Please Enter a New Order Name", Toast.LENGTH_SHORT).show();
												return;}
											if (databaseNameExists(newDatabaseName)) {
												Toast.makeText(getApplicationContext(), "Order name already exists. Please choose another name.", Toast.LENGTH_SHORT).show();
												return;}
											final OrderDataSource dataSource = new OrderDataSource(getApplicationContext(), databaseName);
											dataSource.open();
											List<Order> itemList = dataSource.getAllItems();
											dataSource.changeDatabase(newDatabaseName);
											for (Order item : itemList) {
												String itemName = item.getItemName();
												double itemPrice = item.getItemPrice();
												int itemQuantity = item.getItemQuantity();
												for (int i = 0; i < itemQuantity; i++) {
													dataSource.createItem(itemName, itemPrice);}}
											dataSource.close();
											startStoreCalc(newDatabaseName);}});
								// Cancel Button
								newBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface newDialog, int which) {
											newDialog.dismiss();}});
								AlertDialog newDialog = newBuilder.create();
								newDialog.show();}});
					// Cancel button
					builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();}});
					AlertDialog dialog = builder.create();
					dialog.show();
					return;}});		
        final EditText etCreate = findViewById(R.id.etCreate);
        Button button = findViewById(R.id.btnCreate);
		//Listen for Create Button Click
		button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					databaseName = etCreate.getText().toString().trim();
					if (databaseName.isEmpty()) {
						Toast.makeText(getApplicationContext(), "Please Name Your Order", Toast.LENGTH_SHORT).show();
						etCreate.requestFocus();} 
					else {
						//Check if Typed Name is Already a Saved Order
							if (databaseNameExists(databaseName)) {
								AlertDialog.Builder builder = new AlertDialog.Builder(LoadCreate.this);
								builder.setTitle("Order Name Already Exists");
								builder.setMessage("There is Already an Order With This Name. Would You Like to Open The Existing Order?");
								builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface dialog, int which) {
											startStoreCalc(databaseName);}});
								//If they dont want to use the existing order, try again
								builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface dialog, int which) {
											etCreate.setText("");
											etCreate.requestFocus();}});
								AlertDialog dialog = builder.create();
								dialog.show();
								return;}}
							startStoreCalc(databaseName);}});
		
        // Set focus on EditText, but keep the keyboard hidden until someone clicks on the EditText
        etCreate.requestFocus();
				//Prevent Spaces in Edit Text
				etCreate.setFilters(new InputFilter[] {new InputFilter() {
				public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
					StringBuilder filtered = new StringBuilder();
					for(int i = start; i < end; i++) {
						char character = source.charAt(i);
						if(!Character.isWhitespace(character)) {
							filtered.append(character);
						}}
						return filtered.toString();}}});
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        etCreate.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.showSoftInput(etCreate, InputMethodManager.SHOW_IMPLICIT);
					getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);}});}
    //Begin StoreCalc Activity With Entered/Selected Order Name
    public void startStoreCalc(String databaseName) {
		Intent intent = new Intent(LoadCreate.this, StoreCalc.class);
		intent.putExtra("DATABASE_NAME", databaseName);
		startActivity(intent);}
	private boolean databaseNameExists(String databaseName) {
		for (String savedDatabaseName : databaseNames) {
			if (databaseName.equals(savedDatabaseName)) {
				return true;}}
		return false;}}
