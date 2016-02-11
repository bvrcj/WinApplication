package org.osi.leaveapp.ui;

import org.osi.leaveapp.component.CustomListAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class Summary extends Activity{
	ListView list;
	String[] itemname ={
			"Bereavement Leave",
			"Comp. off ",
			"Probation leave ",
			"Loss of pay ",
			"Marriage leave",
			"Parenity leave"
		};
	
	Integer[] imgid={
			R.drawable.icon,
			R.drawable.icon,
			R.drawable.icon,
			R.drawable.icon,
			R.drawable.icon,
			R.drawable.icon
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d("SummaryScreen","onCreate invoked");  
		setContentView(R.layout.leave_summary);
		
		ImageView home = (ImageView)findViewById(R.id.home);
		home.setOnClickListener(new View.OnClickListener(){
		    public void onClick(View v) {
		    	Log.d("EmployeeProfileScreen","home invoked");  
		    	startActivity(new Intent(Summary.this,Dashboard.class));
		    }
		});
		
		ImageView logout = (ImageView)findViewById(R.id.logout);
		logout.setOnClickListener(new View.OnClickListener(){
		    public void onClick(View v) {
		    	Log.d("EmployeeProfileScreen","Logout invoked");  
		    	startActivity(new Intent(Summary.this,Login.class));
		    }
		});
		
		CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid);
		list=(ListView)findViewById(R.id.list);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String Slecteditem= itemname[+position];
				//Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
				Intent i = new Intent(Summary.this,SummaryDetail.class);   
				i.putExtra("LEAVE_TYPE", Slecteditem);
				startActivity(new Intent(Summary.this,SummaryDetail.class));
				
			}
		});
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d("SummaryScreen","onStart invoked");  
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d("SummaryScreen","onStop invoked");  
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.d("SummaryScreen","onRestart invoked");  
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d("SummaryScreen","onResume invoked");  
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("SummaryScreen","onDestory invoked");  
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d("SummaryScreen","onPause invoked");  
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d("SummaryScreen","onKeyDown invoked");  
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	        // your code
	        return true;
	    }

	    return super.onKeyDown(keyCode, event);
	}
}