package com.adam.app.clientapp;

import com.ada.app.lib.MyParcelable;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	private static final String COMMAND_ACTION = "com.adam.app.parcelservice.Action";
	
	private TextView result = null;
	private MyParcelable parcelableIn = null;
	private String outText = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Debug.printI(TAG, "[onCreate]: enter");
		
		result = (TextView)findViewById(R.id.result);
		
		//register BroadcastReceiver
		IntentFilter intentFilter = new IntentFilter(COMMAND_ACTION);
		this.registerReceiver(receiver, intentFilter);
		
	}
	
	
	public void onStratService(View v)
	{
		Debug.printI(TAG, "[onStratService]: enter");
		Intent intent1 = new Intent();
		intent1.setClassName("com.adam.app.parcelservice", "com.adam.app.parcelservice.CustService");
		this.startService(intent1);
	}
	
	 public class MyBroadcastReceiver extends BroadcastReceiver {

		  @Override
		  public void onReceive(Context context, Intent intent) {

			  String action =  intent.getAction();
			  
			  if(action.equals(COMMAND_ACTION)) {
				  Debug.printI(TAG, "[onReceive]: enter");
				  parcelableIn = intent.getParcelableExtra("TestParcel");
				  outText = "Passed as Parcelable:\n" 
						    + parcelableIn.blogName + "\n" 
						    + parcelableIn.blogAddress;
				  
				  result.setText(outText);
			  }
			  
		  }

	 }
	 
	 private MyBroadcastReceiver receiver = new MyBroadcastReceiver();
}
