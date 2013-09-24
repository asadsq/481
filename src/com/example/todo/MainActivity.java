package com.example.todo;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener, DialogInterface.OnClickListener, OnKeyListener {
	
	EditText txtItem;
	Button btnAdd;
	ListView listItems;
	
	ArrayList<String> toDoItems;
	ArrayAdapter<String> aa;
	
	String[] academics = {"Submit homework", "Study for test", "Buy books"};
	String[] grocery = {"Buy vegetables and meat", "Buy paper plates and cups", "Buy pop corn and soda"};
	String[] bills = {"Pay credit card bill", "Pay phone bill", "Make loan payment", "Pay rent"};	
	String[] other = {"Call home", "Workout", "Sell football ticket"};
	String currentMenu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txtItem = (EditText)findViewById(R.id.txtItem);
		btnAdd = (Button)findViewById(R.id.btnAdd);
		listItems = (ListView)findViewById(R.id.listItems);
		
		btnAdd.setOnClickListener(this);
		txtItem.setOnKeyListener(this);
		
		toDoItems = new ArrayList<String>();
		aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, toDoItems);
		listItems.setAdapter(aa);		
	}
	
	private void addItem(String item)
	{
		if (item.length() > 0)
		{
			Toast.makeText(getApplicationContext(), item + " added", Toast.LENGTH_SHORT).show();
			this.toDoItems.add(item);
			this.aa.notifyDataSetChanged();
			this.txtItem.setText("");
		}
	}
			
	private void displayPopup(String title, String[] items)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setItems(items, this);
		builder.show();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		
		super.onCreateOptionsMenu(menu);
		MenuItem item;
		item = menu.add("Academics");
					
		item = menu.add("Grocery");
								
		item = menu.add("Bills");		
		
		item = menu.add("Other");				
				
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item)
	{
		super.onOptionsItemSelected(item);
		if (item.hasSubMenu() == false)
		{
			if (item.getTitle() == "Academics")
			{
				currentMenu = "Academics";
				this.displayPopup("Academics", this.academics);
			}
			if (item.getTitle() == "Grocery")
			{
				currentMenu = "Grocery";
				this.displayPopup("Grocery", this.grocery);
			}
			if (item.getTitle() == "Bills")
			{
				currentMenu = "Bills";
				this.displayPopup("Bills", this.bills);
			}
			if (item.getTitle() == "Other")
			{
				currentMenu = "Other";
				this.displayPopup("Other", this.other);
			}			
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		if (v == this.btnAdd)
		{
			this.addItem(this.txtItem.getText().toString());
		}		
	}
	
	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DPAD_CENTER)
		{
			this.addItem(this.txtItem.getText().toString());

		}
		return false;
	}

	@Override
	public void onClick(DialogInterface dialog, int item) {
		if (currentMenu == "Academics")
		{
			this.addItem(academics[item]);
		}
		if (currentMenu == "Grocery")
		{
			this.addItem(grocery[item]);
		}
		if (currentMenu == "Bills")
		{
			this.addItem(bills[item]);
		}
		if (currentMenu == "Other")
		{
			this.addItem(other[item]);
		}
		
	}
}
