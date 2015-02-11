package com.adam.app.parcelservice;


import com.ada.app.lib.MyParcelable;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class CustService extends Service {
	
	private static final String TAG = "CustService";
	private MyParcelable myParcelable = null;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Debug.PrintI(TAG, "[onCreate]: enter");
		
		//prepare MyParcelable passing to intentMyIntentService
		myParcelable = new MyParcelable();
		myParcelable.blogName = "Android-er";
		myParcelable.blogAddress = "http://android-er.blogspot.com/";
		
		
		
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		Debug.PrintI(TAG, "[onBinder]: enter");
		return binder;
	}
	
	
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Debug.PrintI(TAG, "[onStartCommand]: enter");
		//send parcel by broadcast
		Intent intent1 = new Intent();
		intent1.setAction("com.adam.app.parcelservice.Action");
		intent1.putExtra("TestParcel", myParcelable);
		this.sendBroadcast(intent1);
				
		return super.onStartCommand(intent, flags, startId);
	}




	class BinderCustService extends ICommandService.Stub {

		@Override
		public void sendCommandTest() throws RemoteException {
			// TODO Auto-generated method stub
			
		}
				
	}
	
	private BinderCustService binder = new BinderCustService();
}
