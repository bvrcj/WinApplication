package org.osi.leaveapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Dashboard extends Activity {
	Button emp_profile;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("DashboardScreen", "onCreate invoked");
		setContentView(R.layout.main_layout);

		emp_profile = (Button) findViewById(R.id.emp_profile);
		emp_profile.setOnClickListener(new ScreenNavigation());

		Button emp_apply_leave = (Button) findViewById(R.id.emp_apply_leave);
		emp_apply_leave.setOnClickListener(new ScreenNavigation());

		Button emp_requisitions_leave = (Button) findViewById(R.id.emp_requisitions_leave);
		emp_requisitions_leave.setOnClickListener(new ScreenNavigation());

		Button emp_leave_summary = (Button) findViewById(R.id.emp_leave_summary);
		emp_leave_summary.setOnClickListener(new ScreenNavigation());

		Button emp_leave_approve = (Button) findViewById(R.id.emp_leave_approve);
		emp_leave_approve.setOnClickListener(new ScreenNavigation());
		
		ImageView logout = (ImageView)findViewById(R.id.logout);
		logout.setOnClickListener(new View.OnClickListener(){
		    public void onClick(View v) {
		    	Log.d("EmployeeProfileScreen","Logout invoked");  
		    	startActivity(new Intent(Dashboard.this,Login.class));
		    }
		});

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d("DashboardScreen", "onStart invoked");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d("DashboardScreen", "onStop invoked");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.d("DashboardScreen", "onRestart invoked");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d("DashboardScreen", "onResume invoked");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("DashboardScreen", "onDestory invoked");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d("DashboardScreen", "onPause invoked");
	}

	private class ScreenNavigation implements OnClickListener {

		@Override
		public void onClick(View v) {
			int id = v.getId();
			switch (id) {
			case R.id.emp_profile: 
				startActivity(new Intent(Dashboard.this, Profile.class));
				break;
			case R.id.emp_apply_leave: 
				startActivity(new Intent(Dashboard.this, Leave.class));
				break;
			case R.id.emp_requisitions_leave: 
				startActivity(new Intent(Dashboard.this, Requisition.class));
				break;
			case R.id.emp_leave_summary: 
				startActivity(new Intent(Dashboard.this, Summary.class));
				break;
			}
		}
	}
}