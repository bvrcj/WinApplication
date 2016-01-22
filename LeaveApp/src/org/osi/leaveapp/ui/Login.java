package org.osi.leaveapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class Login extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d("LoginScreen","onCreate invoked");  
		setContentView(R.layout.login_layout);
		
		final Button button = (Button) findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                startActivity(new Intent(Login.this, Dashboard.class));
                // close this activity
                finish();
            }
        });
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d("LoginScreen","onStart invoked");  
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d("LoginScreen","onStop invoked");  
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.d("LoginScreen","onRestart invoked");  
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d("LoginScreen","onResume invoked");  
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("LoginScreen","onDestory invoked");  
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d("LoginScreen","onPause invoked");  
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d("EmployeeProfileScreen","onKeyDown invoked");  
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	        // your code
	    	System.exit(0);
	        return true;
	    }

	    return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public void onBackPressed() {
	    Log.d("EmployeeProfileScreen","onBackPressed invoked");
	    System.exit(0);
	}
}