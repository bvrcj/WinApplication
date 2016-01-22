package org.osi.leaveapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

public class Leave extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d("LeaveScreen","onCreate invoked");  
		setContentView(R.layout.leave_layout);
		
		ImageView home = (ImageView)findViewById(R.id.home);
		home.setOnClickListener(new View.OnClickListener(){
		    public void onClick(View v) {
		    	Log.d("EmployeeProfileScreen","home invoked");  
		    	startActivity(new Intent(Leave.this,Dashboard.class));
		    }
		});
		
		ImageView logout = (ImageView)findViewById(R.id.logout);
		logout.setOnClickListener(new View.OnClickListener(){
		    public void onClick(View v) {
		    	Log.d("EmployeeProfileScreen","Logout invoked");  
		    	startActivity(new Intent(Leave.this,Login.class));
		    }
		});
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d("LeaveScreen","onStart invoked");  
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d("LeaveScreen","onStop invoked");  
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.d("LeaveScreen","onRestart invoked");  
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d("LeaveScreen","onResume invoked");  
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("LeaveScreen","onDestory invoked");  
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d("LeaveScreen","onPause invoked");  
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d("LeaveScreen","onKeyDown invoked");  
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	        // your code
	        return true;
	    }

	    return super.onKeyDown(keyCode, event);
	}
}
