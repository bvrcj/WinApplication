package org.osi.leaveapp.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.osi.leaveapp.constants.AppConstants;
import org.osi.leaveapp.service.ServiceHandler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity{
	
	private ProgressDialog pDialog;
	
	private EditText mUsername;
	private EditText mPassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d("LoginScreen","onCreate invoked");  
		setContentView(R.layout.login_layout);
		mUsername = (EditText)findViewById(R.id.userName);
		mPassword= (EditText)findViewById(R.id.password);
		
		final Button button = (Button) findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	// Calling async task to get response
        		//new LoginEssPortal().execute();
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
		Log.d("LoginScreen","onKeyDown invoked");  
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	        // your code
	    	System.exit(0);
	        return true;
	    }

	    return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public void onBackPressed() {
	    Log.d("LoginScreen","onBackPressed invoked");
	    System.exit(0);
	}
	
	

	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class LoginEssPortal extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(Login.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			/*params.add(new BasicNameValuePair("userName", "vbommaraju@nisum.com"));
			params.add(new BasicNameValuePair("password", "VB$123"));*/
			params.add(new BasicNameValuePair("userName", mUsername.getText().toString()));
			params.add(new BasicNameValuePair("password", mPassword.getText().toString()));
			
			// Making a request to url and getting response
			String resposeMsg = sh.makeServiceCall(AppConstants.END_POINT, ServiceHandler.POST,params);

			Log.d("Response: ", "> " + resposeMsg);

			if (resposeMsg != null) {
				//resposeMsg = sh.makeServiceCall(AppConstants.LEAVE_DASHBOARD, ServiceHandler.GET);
				
				//Log.d("Leave Dashboard Response: ", "> " + resposeMsg);
				
				startActivity(new Intent(Login.this, Dashboard.class));
				// close this activity
                finish();
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			
		}

	}
	
	
	
}