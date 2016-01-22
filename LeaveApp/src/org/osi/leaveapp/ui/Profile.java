package org.osi.leaveapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

public class Profile extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d("EmployeeProfileScreen","onCreate invoked");  
		setContentView(R.layout.employee_layout);
		
		ImageView home = (ImageView)findViewById(R.id.home);
		home.setOnClickListener(new View.OnClickListener(){
		    public void onClick(View v) {
		    	Log.d("EmployeeProfileScreen","home invoked");  
		    	startActivity(new Intent(Profile.this,Dashboard.class));
		    }
		});
		
		ImageView logout = (ImageView)findViewById(R.id.logout);
		logout.setOnClickListener(new View.OnClickListener(){
		    public void onClick(View v) {
		    	Log.d("EmployeeProfileScreen","Logout invoked");  
		    	startActivity(new Intent(Profile.this,Login.class));
		    }
		});
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d("EmployeeProfileScreen","onStart invoked");  
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d("EmployeeProfileScreen","onStop invoked");  
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.d("EmployeeProfileScreen","onRestart invoked");  
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d("EmployeeProfileScreen","onResume invoked");  
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("EmployeeProfileScreen","onDestory invoked");  
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d("EmployeeProfileScreen","onPause invoked");  
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d("EmployeeProfileScreen","onKeyDown invoked");  
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	        // your code
	        return true;
	    }

	    return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public void onBackPressed() {
	    Log.d("EmployeeProfileScreen","onBackPressed invoked");  
	}
}
