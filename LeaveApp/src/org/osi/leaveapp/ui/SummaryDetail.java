package org.osi.leaveapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SummaryDetail extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d("SummaryDetailScreen","onCreate invoked");  
		setContentView(R.layout.summary_detail_layout);
	
		Intent intent = getIntent();
		String name = intent.getStringExtra("LEAVE_TYPE");
		
		TextView leave_type = (TextView) findViewById(R.id.leave_type_is);
		leave_type.setText(name);
		 
		ImageView home = (ImageView)findViewById(R.id.home);
		home.setOnClickListener(new View.OnClickListener(){
		    public void onClick(View v) {
		    	Log.d("SummaryDetailScreen","home invoked");  
		    	startActivity(new Intent(SummaryDetail.this,Dashboard.class));
		    }
		});
		
		ImageView logout = (ImageView)findViewById(R.id.logout);
		logout.setOnClickListener(new View.OnClickListener(){
		    public void onClick(View v) {
		    	Log.d("SummaryDetailScreen","Logout invoked");  
		    	startActivity(new Intent(SummaryDetail.this,Login.class));
		    }
		});
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d("SummaryDetailScreen","onStart invoked");  
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d("SummaryDetailScreen","onStop invoked");  
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.d("SummaryDetailScreen","onRestart invoked");  
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d("SummaryDetailScreen","onResume invoked");  
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("SummaryDetailScreen","onDestory invoked");  
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d("SummaryDetailScreen","onPause invoked");  
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d("SummaryDetailScreen","onKeyDown invoked");  
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	        // your code
	        return true;
	    }

	    return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public void onBackPressed() {
	    Log.d("SummaryDetailScreen","onBackPressed invoked");  
	}
}
