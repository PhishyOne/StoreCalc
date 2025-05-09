/*
To Do List:
Borders and background for saved orders listview 
move item names and prices to database
make new app to edit name and prices database
visual upgrades
add pictures next to categories and items
Adding the total to database list
Make delete buttons in database list
Make plus and minus buttons in items list (delete button too?)
*/
package com.td.storecalc;

import android.app.Dialog;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.view.GravityCompat;
import android.view.View;
import android.view.WindowManager;
import android.view.LayoutInflater;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.ExpandableListAdapter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.text.DecimalFormat;
import Order;

public class StoreCalc extends AppCompatActivity
 {
    private String databaseName, selItemName, dfsFunds, dfrFunds;
	private Double selItemPrice, rFunds, sfFundsDbl;
	private OrderDataSource dataSource;
	private Button mButtonCreateLoad, mButtonSaveOrder, mButtonClearOrder, mButtonShowOrder,
	mButtonHidePopup, mButtonMinus, mButtonPlus, mButtonSfunds, mButtonEditSFunds;
	private TextView titleText, NumItem, Total, itemQuantity, SFunds, RFunds, selItem;
	private CurrencyEditText mSFunds;
	private ExpandableListView.OnChildClickListener onChildClickListener;
	private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;
	private Boolean saved;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.store_calc);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
		saved = false;
        // Get the database name and checkbox state from the intent
        Intent intent = getIntent();
        databaseName = intent.getStringExtra("DATABASE_NAME");
		//Begin Store Calc Sequence
		hideSC();
		showSF();
		menuBtnListen();
	}
///////MENU///////
	//Listen For Menu Button Clicks
	public void menuBtnListen(){
		//Listen For Create/Load Order Button Click
		mButtonCreateLoad = findViewById(R.id.btnCreateLoad);
		mButtonCreateLoad.setOnClickListener(new View.OnClickListener() {
				@Override
				//When Create/Load Order Button is Clicked Return to LoadCreate Activity
				public void onClick(View v) {
					// create a new intent to start the new activity
					Intent intent = new Intent(StoreCalc.this, LoadCreate.class);
					// Start the Load/Create activity
					startActivity(intent);
					// Finish the current activity
					finish();
				}});
		//Listen For Save Order Button Click
		mButtonSaveOrder = findViewById(R.id.btnMenuSave);
		mButtonSaveOrder.setOnClickListener(new View.OnClickListener() {
				@Override
				//When Save Order Button is Clicked 
				public void onClick(View v) {
					Toast.makeText(StoreCalc.this, "Order Saved", Toast.LENGTH_SHORT).show();
					saved = true;
					showItemListPopup();
				}});	
		//Listen For Start Over Button Click
		mButtonClearOrder = findViewById(R.id.btnMenuStartOver);
		mButtonClearOrder.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					final OrderDataSource dataSource = new OrderDataSource(getApplicationContext(), databaseName);
					new AlertDialog.Builder(StoreCalc.this)
						.setTitle("Start Over")
						.setMessage("This will clear the current order. Are you sure?")
						.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								// Clear Database
								dataSource.open();
								dataSource.clearDatabase();
								dataSource.close();
								setTotal();
								setRFunds();
								hideSC();
								showSF();
								sFundsSetup();
							}})
						.setNegativeButton(android.R.string.no, null)
						.setIcon(android.R.drawable.ic_dialog_alert)
						.show();
				}});
		//Listen For Show Order Button Click
		mButtonShowOrder = findViewById(R.id.btnMenuShowOrder);
		mButtonShowOrder.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//Popup Order List
					showItemListPopup();
				}});
		//Listen for Edit Starting Funds Button Click
		mButtonEditSFunds = findViewById(R.id.btnEditSFunds);
		mButtonEditSFunds.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			hideSC();
			showSF();
			sFundsSetup();
		}});}
/////////////
//////STARTING FUNDS//////
	//Setup Starting Funds
	public void sFundsSetup() {
		mSFunds = findViewById(R.id.etStartingFunds);
		mButtonSfunds = findViewById(R.id.btnStartingFunds);
		mSFunds.requestFocus();
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(mSFunds, InputMethodManager.SHOW_IMPLICIT);
		//When Enter Button is Clicked Check and Set Starting Funds
		mButtonSfunds.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					hideSoftKeyboard();
					ckSFunds();
				}});
	}
	//Ensure Valid Starting Funds are Entered
	public void ckSFunds() {
		boolean isValid = false;
		while (!isValid) {
			mSFunds.requestFocus();
			// Prompt the user for input
			String input = mSFunds.getText().toString().trim();
			// Convert input to double
			double dblsFunds;
			//Check for Invalid Characters
			try {dblsFunds = Double.parseDouble(input);} 
			catch (NumberFormatException e) {
				Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show();
				mSFunds.setText("");
				return; // Restart the loop
			}
			// Check for valid amount
			if (dblsFunds < 1 || dblsFunds > 80) {
				Toast.makeText(this, "Amount must be between $1 and $80", Toast.LENGTH_SHORT).show();
				mSFunds.setText("");
				return; // Restart the loop
			}
			isValid = true; // Exit the loop if input is valid
		}
		// Continue with the rest of the setup
		hideSoftKeyboard();
		unhideSC();
		findViewById(R.id.layItemQuantity).setVisibility(View.GONE);
		setSFunds();
		hideSF();
		loadDataFromDatabase(databaseName);
		mSFunds.setText("");
		if (rFunds < 0) {
			fundsNegPopup();}
	}
	public void setSFunds(){
		double dblsFunds = mSFunds.getCleanDoubleValue();
		//Format sFunds With Two Digits Behind Decimal
		DecimalFormat dfSF = new DecimalFormat("#.00");
		//Turn it Into a String for Output
		dfsFunds = dfSF.format(dblsFunds);
		//Set variable SFunds to txtFunds TextView
		SFunds = findViewById(R.id.txtFunds);
		//Replace txtFunds Content to Starting Funds Value
		SFunds.setText("This Week's Funds: $" + dfsFunds);
		sfFundsDbl = Double.parseDouble(dfsFunds);
	}
/////////////
//////TOTAL//////
    public void setTotal() {
        //Set variable Total to txtTotal TextView
        Total = findViewById(R.id.txtTotal);
        //Format total With Two Digits Behind Decimal
		DecimalFormat dfTotal = new DecimalFormat("#0.00");
		//Turn it Into a String for Output
		String dftotal = (getTotal() == 0.00) ? "0.00" : dfTotal.format(getTotal());
		//Set txtTotal to Total Value
		Total.setText("Total Spent: $" + dftotal);
	}
	public double getTotal(){
		OrderDataSource dataSource = new OrderDataSource(this, databaseName);
		dataSource.open();
		double orderTotal = 0;
		orderTotal = dataSource.getOrderTotal();
		dataSource.close();
		return orderTotal;
	}
////////////
////////REMAINING FUNDS//////
	public void setRFunds() {
		// set Remaining Funds to Value of Starting Funds Minus Total Spent
		rFunds = sfFundsDbl - getTotal();
		// set variable RFunds to txtRemainingFunds TextView
		RFunds = findViewById(R.id.txtRemainingFunds);
		// Format rFunds With Two Digits Behind Decimal
		DecimalFormat dfRF = new DecimalFormat("#.00");
		// Turn it Into a String for Output
		dfrFunds = dfRF.format(rFunds);
		// Set txtRemainingFunds to Starting Value
		RFunds.setText("Remaining Funds: $" + dfrFunds);
		RFunds.setTextColor(getColorForValue(rFunds));}
		//Return Gradient Color Based on Remaining Funds' Current Value
		private int getColorForValue(double value) {
			double ratio = 1.0 - (value / 80.0);
			int red, green;
			if (ratio <= 0.5) {
				// green to yellow
				green = 255;
				red = (int)(255 * (2.0 * ratio));
			} else {
				// yellow to red
				green = (int)(255 * (2.0 - 2.0 * ratio));
				red = 255;
			}
			return Color.rgb(red, green, 0);
			}
/////////////
//////TITLE//////
	//Grab the title of this order from the DB name
	public void showTitleInSC(){
		titleText = findViewById(R.id.txtTitleInSC);
		titleText.setText("Order: " + databaseName);
	}
////////////
//////POPUP//////
	public void showItemListPopup() {
		// Retrieve data from the database
		dataSource = new OrderDataSource(this, databaseName);
		dataSource.open();
		final List<Order> itemList = dataSource.getAllItems();
		dataSource.close();
		// Inflate the layout for the popup dialog
		final LayoutInflater inflater = LayoutInflater.from(this);
		final View popupView = inflater.inflate(R.layout.popup_item_list, null);
		// Find the ListView in the layout and set the adapter
		final ListView listView = popupView.findViewById(R.id.item_list_view);
		final ArrayAdapter<Order> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
		listView.setAdapter(adapter);
		// Create a Dialog object and set the content view
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(popupView);
		//Set Correct Title
		final TextView mTxtPopupTitle = popupView.findViewById(R.id.item_list_title);
		mTxtPopupTitle.setText("Current Order");
		//Set Total in Window
		final TextView mTxtPopupTotal = popupView.findViewById(R.id.txtPopupTotal);
		mTxtPopupTotal.setVisibility(View.VISIBLE);
		DecimalFormat dfTotal = new DecimalFormat("#0.00");
		//Turn it Into a String for Output
		String dftotal = (getTotal() == 0.00) ? "0.00" : dfTotal.format(getTotal());
		mTxtPopupTotal.setText("$"+dftotal);
		// Show the Popup Window
		dialog.show();
		// Set an item click listener for the ListView
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					int groupCount = expandableListAdapter.getGroupCount();
					for (int i = 0; i < groupCount; i++) {expandableListView.collapseGroup(i);}
					// Get the selected item from the list
					Order selectedItem = itemList.get(position);
					// Find the group and child position for the selected item in the expandable list
					int groupPosition = -1, childPosition = -1;
					String selectedItemName = selectedItem.getItemName(); // Assuming Order class has a method to get item name
					for (int i = 0; i < expandableListTitle.size(); i++) {
						List<String> children = expandableListDetail.get(expandableListTitle.get(i));
						for (int j = 0; j < children.size(); j++) {
							if (getSelName(i, j).equals(selectedItemName)) {
								groupPosition = i;
								childPosition = j;
								dialog.hide();
								break;
							}}
						if (groupPosition != -1) {
							break;
						}}
					// Expand the group and scroll to the child item in the expandable list view
					if (groupPosition != -1 && childPosition != -1) {
						expandableListView.expandGroup(groupPosition);
						expandableListView.setSelectedChild(groupPosition, childPosition, true);
						handleChildClick(groupPosition, childPosition);
					}}});
		mButtonHidePopup = popupView.findViewById(R.id.btnPopupHide);
		mButtonHidePopup.setOnClickListener(new View.OnClickListener() {
				@Override
				// When Hide Button is Clicked
				public void onClick(View v) {
					dialog.hide();
					if(saved){
						saved = false;
						// create a new intent to start the new activity
						Intent intent = new Intent(StoreCalc.this, LoadCreate.class);
						// Start the Load/Create activity
						startActivity(intent);}}});
	}
	public void fundsNegPopup(){
		final AlertDialog.Builder builder = new AlertDialog.Builder(StoreCalc.this);
		builder.setTitle("Your Remaining Funds Are Negative");
		builder.setMessage("Please Adjust Item Quantity or This Week's Funds");
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					//If Remaining Funds are Negative, Show Item List After Done
					if(rFunds < 0){showItemListPopup();}}});
		AlertDialog dialog = builder.create();
		dialog.show();}
	//"Click" the Selected Item in Expandable Listview When Clicking on the item in the list
	private void handleChildClick(int groupPosition, int childPosition) {
		final OrderDataSource dataSource = new OrderDataSource(getApplicationContext(), databaseName);
		findViewById(R.id.layItemQuantity).setVisibility(View.VISIBLE);
		findViewById(R.id.layBorder2).setVisibility(View.VISIBLE);
		LinearLayout fundsLayout = findViewById(R.id.laySFunds);
		((RelativeLayout.LayoutParams) fundsLayout.getLayoutParams()).removeRule(RelativeLayout.CENTER_VERTICAL);
		selItemName = getSelName(groupPosition, childPosition);
		selItemPrice = getSelPrice(groupPosition, childPosition);
		String formatted = String.format("%.2f", selItemPrice);
		selItem = findViewById(R.id.txtNameItem);
		selItem.setText(selItemName + " -- $" + formatted + " each");
		dataSource.open();
		setQuantity(dataSource.getItemQuantity(selItemName));
		dataSource.close();
	}
////////////
//////PLUS & MINUS BUTTONS & SELCTED ITEM QUANTITY//////
	public void plusMinus() {
		itemQuantity = findViewById(R.id.txtNumItem);
		mButtonMinus = findViewById(R.id.btnOneLess);
		mButtonPlus = findViewById(R.id.btnOneMore);
		// When Minus Button is Clicked
		mButtonMinus.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					final OrderDataSource dataSource = new OrderDataSource(getApplicationContext(), databaseName);
					// Subtract selected item from database
					dataSource.open();
					dataSource.deleteOrDecrementItem(selItemName);
					setQuantity(dataSource.getItemQuantity(selItemName));
					dataSource.close();
					setTotal();
					setRFunds();
				}});
		// When Plus Button is Clicked
		mButtonPlus.setOnClickListener(new View.OnClickListener() {
				@Override
				// When Plus Button is Clicked
				public void onClick(View v) {
					final OrderDataSource dataSource = new OrderDataSource(getApplicationContext(), databaseName);
					// Add selected item to database
					dataSource.open();
					dataSource.createItem(selItemName, selItemPrice);
					setQuantity(dataSource.getItemQuantity(selItemName));
					dataSource.close();
					setTotal();
					setRFunds();
					if (rFunds < 0) {
						fundsNegPopup();	
					}}});}
	//Update the Selected Item Quantity
	public void setQuantity(int numItem){
		//Set variable NumItem to txtNumItem TextView
		NumItem = findViewById(R.id.txtNumItem);
		//Replace txtFunds Content to Starting Funds Value
		NumItem.setText(String.valueOf(numItem));      
	}
////////////
//////DATABASES//////
	private void loadDataFromDatabase(String databaseName) {
		Order order = new Order();
		String fileName = databaseName + ".db";
		order.setTitle(fileName);
		setTotal();
		setRFunds();
		plusMinus();
	}
////////////
//////KEYBOARD CONTROL//////
	//Hide Keyboard
	public void hideSoftKeyboard(){
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm != null) {
			imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
		}
	}
/////////////
//////EXPANDABLE LISTVIEW///////
	public void expLVSetup(){
		//Setup Parent Expandable Listview For Categories Holding Children Items
		expandableListView = findViewById(R.id.expandableListView);
		expandableListDetail = ExpandableListDataPump.getData();
		expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
		expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
		expandableListView.setAdapter(expandableListAdapter);
		//When Expandable Listview for Items in a Category is Expanded
		expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
				@Override
				public void onGroupExpand(int groupPosition) {
//					Toast.makeText(getApplicationContext(),
//								   expandableListTitle.get(groupPosition) + " List Expanded.",
//								   Toast.LENGTH_SHORT).show();
				}});
		//When Expandable Listview for Items in a Category is Collapsed
		expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
				@Override
				public void onGroupCollapse(int groupPosition) {
//					Toast.makeText(getApplicationContext(),
//								   expandableListTitle.get(groupPosition) + " List Collapsed.",
//								   Toast.LENGTH_SHORT).show();
				}});
		// When Items in a Category are Clicked
		expandableListView.setOnChildClickListener(onChildClickListener = new ExpandableListView.OnChildClickListener() {
													   @Override
													   public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
														   handleChildClick(groupPosition, childPosition);
					final OrderDataSource dataSource = new OrderDataSource(getApplicationContext(), databaseName);
					findViewById(R.id.layItemQuantity).setVisibility(View.VISIBLE);
					findViewById(R.id.layBorder2).setVisibility(View.VISIBLE);
					LinearLayout fundsLayout = findViewById(R.id.laySFunds);
					((RelativeLayout.LayoutParams)fundsLayout.getLayoutParams()).removeRule(RelativeLayout.CENTER_VERTICAL);
					selItemName = getSelName(groupPosition, childPosition);
					selItemPrice = getSelPrice(groupPosition, childPosition);
					String formatted = String.format("%.2f", selItemPrice);
					selItem = findViewById(R.id.txtNameItem);
					selItem.setText(selItemName + " -- $" + formatted + " each");
//					Toast.makeText(getApplicationContext(), expandableListTitle.get(groupPosition) + " -> " + expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
					dataSource.open();
					setQuantity(dataSource.getItemQuantity(selItemName));
					dataSource.close();
					return false;
				}});
	}
	//Return Selected Item Name from ExpandableListView
	public String getSelName(int groupPosition, int childPosition){
		String itemAndPrice = expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition);
		Pattern pattern = Pattern.compile("(\\S.*\\S)\\s+\\$\\s*(\\d+\\.\\d+)");
		Matcher matcher = pattern.matcher(itemAndPrice);
		if (matcher.find()) {
			selItemName = matcher.group(1);
			return selItemName;
		} else {
			return "";
		}
	}
    //Return Selected Item Price from ExpandableListView
	public double getSelPrice(int groupPosition, int childPosition){
		String itemAndPrice = expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition);
		Pattern pattern = Pattern.compile("(\\S.*\\S)\\s+\\$\\s*(\\d+\\.\\d+)");
		Matcher matcher = pattern.matcher(itemAndPrice);
		if (matcher.find()) {
			selItemPrice = Double.parseDouble(matcher.group(2));
			return selItemPrice;
		} else {
			return 0;
		}
	}
/////////////
///////SHOW AND HIDE//////
	//Hide Everything
	public void hideSC(){
		findViewById(R.id.expandableListView).setVisibility(View.GONE);
		findViewById(R.id.layFundsTotal).setVisibility(View.GONE);
		findViewById(R.id.layItemQuantity).setVisibility(View.GONE);
		findViewById(R.id.layButton).setVisibility(View.GONE);
		findViewById(R.id.laySFunds).setVisibility(View.GONE);
		showTitleInSC();}
	//Show Everything
	public void unhideSC(){
		findViewById(R.id.layButton).setVisibility(View.VISIBLE);
		findViewById(R.id.layFundsTotal).setVisibility(View.VISIBLE);
		findViewById(R.id.layItemQuantity).setVisibility(View.VISIBLE);
		findViewById(R.id.expandableListView).setVisibility(View.VISIBLE);
		findViewById(R.id.center_anchor).setVisibility(View.VISIBLE);
		showTitleInSC();
		expLVSetup();
		}
	//Show Layout for Starting Funds
	public void showSF(){
		findViewById(R.id.laySFunds).setVisibility(View.VISIBLE);
		findViewById(R.id.layHorSFunds).setVisibility(View.VISIBLE);
		findViewById(R.id.txtStarting).setVisibility(View.VISIBLE);
		findViewById(R.id.txtDollarSign).setVisibility(View.VISIBLE);
		findViewById(R.id.etStartingFunds).setVisibility(View.VISIBLE);
		findViewById(R.id.btnStartingFunds).setVisibility(View.VISIBLE);
		sFundsSetup();
	}
	public void hideSF(){
		findViewById(R.id.laySFunds).setVisibility(View.GONE);
	}
////////////
}

