package org.osi.ui;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;
import org.osi.constants.AppConstants;
import org.osi.network.ConnectionDetector;
import org.osi.network.ServiceHandler;
import org.osi.receiver.AlarmManagerBroadcastReceiver;
import org.osi.snacksapp.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * 
 * @author vbommaraju
 * 
 */
public class MenuActivity extends Activity {

	private AlarmManagerBroadcastReceiver alarm;
	private ProgressDialog pDialog;
	// flag for Internet connection status
	Boolean isInternetPresent = false;

	// Connection detector class
	ConnectionDetector cd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_layout);
		
		alarm = new AlarmManagerBroadcastReceiver();
		
		// creating connection detector class instance
        cd = new ConnectionDetector(getApplicationContext());
		
        RadioButton r1 = (RadioButton) findViewById(R.id.radio_button1);
		r1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				AppConstants.OSI_CAMPUS = "1";
				// Toast.makeText(getApplicationContext(),
				// "OSI CAMPUS >>> "+AppConstants.OSI_CAMPUS,
				// Toast.LENGTH_LONG).show();
			}
		});

		RadioButton r2 = (RadioButton) findViewById(R.id.radio_button2);
		r2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				AppConstants.OSI_CAMPUS = "2";
				// Toast.makeText(getApplicationContext(),
				// "OSI CAMPUS >>> "+AppConstants.OSI_CAMPUS,
				// Toast.LENGTH_LONG).show();
			}
		});

		Button startBtn = (Button) findViewById(R.id.start);
		startBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 // get Internet status
                isInternetPresent = cd.isConnectingToInternet();
                // check for Internet status
                if (isInternetPresent) {
                    // Internet Connection is Present then make HTTP requests
                	startRepeatingTimer(v);
                }else{
                	// Internet connection is not present Ask user to connect to Internet
                    showAlertDialog(MenuActivity.this, "No Internet Connection","You don't have internet connection.", false);
                }
			}
		});

		Button cancelBtn = (Button) findViewById(R.id.cancel);
		cancelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				cancelRepeatingTimer(v);
			}
		});

		Button todayBtn = (Button) findViewById(R.id.today);
		todayBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 // get Internet status
                isInternetPresent = cd.isConnectingToInternet();
                // check for Internet status
                if (isInternetPresent) {
                    // Internet Connection is Present then make HTTP requests
    				//startOneTimeTimer(v);
    				new SnacksCall().execute();
                }else{
                	// Internet connection is not present Ask user to connect to Internet
                    showAlertDialog(MenuActivity.this, "No Internet Connection","You don't have internet connection.", false);
                }

			}
		});

		ImageView close = (ImageView) findViewById(R.id.close);
		close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	public void startRepeatingTimer(View view) {
		Context context = this.getApplicationContext();
		if (alarm != null) {
			alarm.SetAlarm(context);
			Toast.makeText(context, "Snack Serivce Notification Enabled",
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(context, "Snack Serivce Exeption",
					Toast.LENGTH_SHORT).show();
		}
	}

	public void cancelRepeatingTimer(View view) {
		Context context = this.getApplicationContext();
		if (alarm != null) {
			alarm.CancelAlarm(context);
			Toast.makeText(context, "Snack Serivce Notification Disabled",
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(context, "Snack Serivce Exeption",
					Toast.LENGTH_SHORT).show();
		}
	}

	public void startOneTimeTimer(View view) {
		Context context = this.getApplicationContext();
		if (alarm != null) {
			alarm.setOnetimeTimer(context);
			// Toast.makeText(context, "Snack Serivce Notification Enabled",
			// Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(context, "Snack Serivce Exeption",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_widget_alarm_manager, menu);
		return true;
	}
	
	/**
     * Function to display simple Alert Dialog
     * @param context - application context
     * @param title - alert dialog title
     * @param message - alert message
     * @param status - success/failure (used to set icon)
     * */
    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
 
        // Setting Dialog Title
        alertDialog.setTitle(title);
 
        // Setting Dialog Message
        alertDialog.setMessage(message);
         
        // Setting alert dialog icon
        alertDialog.setIcon((status) ? R.drawable.success : R.drawable.failure);
 
        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
 
        // Showing Alert Message
        alertDialog.show();
    }
    
    /**
	 * Async task class to get json by making HTTP call
	 * */
	private class SnacksCall extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = ProgressDialog.show(
		                MenuActivity.this,
		                "Snack App",
		                "Please wait...",
		                true,
		                true,
		                new DialogInterface.OnCancelListener(){
		                    @Override
		                    public void onCancel(DialogInterface dialog) {
		                    	MenuActivity.this.cancel(true);
		                        finish();
		                    }
		                }
		        );
			
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			
			String resposeMsg="";
			
			Calendar c = Calendar.getInstance();
			Log.i("Current time => " , ""+c.getTime());
			
			SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");
			Log.i("Simple Date Format",""+c.getTime());
			// Output "Wed Sep 26 14:23:28 EST 2012"

			String formatted = format1.format(c.getTime());
			String formattedDate = formatted;

			Log.i("FORMATTED DATE >>>> ",formattedDate);
			//Toast.makeText(context, formattedDate, Toast.LENGTH_LONG).show();
			
			
			SimpleDateFormat sdf_ = new SimpleDateFormat("EEEE");
			Date date = new Date();
			String dayName = sdf_.format(date);

			AppConstants.DAY = formattedDate + " - " + dayName;

			//Toast.makeText(context, AppConstants.DAY, Toast.LENGTH_LONG).show();

			if (!(dayName.equalsIgnoreCase(AppConstants.STOP_SATURDAY))
					|| !(dayName.equalsIgnoreCase(AppConstants.STOP_SUNDAY))) {
			
				ServiceHandler sh = new ServiceHandler();		
				//String day ="3-2-2016 - Wednesday";
				String day=AppConstants.DAY;
				day=day.replace(" ", "%20");
				//Toast.makeText(getBaseContext(), AppConstants.DAY, Toast.LENGTH_LONG).show();
				Intent snackAlert= new Intent(MenuActivity.this, SnacksTimeActivity.class);
				snackAlert.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				try {
					resposeMsg = sh.makeServiceCall(AppConstants.END_POINT+AppConstants.OSI_CAMPUS+"/"+day+"/", ServiceHandler.GET,null);
					Log.d("Response: ", "> " + resposeMsg);
					Log.i("Response: ", ">>>>>>>>>>>> " + resposeMsg);
					Log.w("Response: ", "><<<<<<<<<<<<<<<>>>>>>>>>> " + resposeMsg);
					if (resposeMsg != null) {
						try {
							JSONObject  serverResponse = new JSONObject(resposeMsg);
							snackAlert.putExtra("SnacksName", serverResponse.getString("snacksName").toString().trim());
							snackAlert.putExtra("SnacksDate", serverResponse.getString("date").toString());
							snackAlert.putExtra("SnacksCampus", serverResponse.getString("campus").toString());
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							Log.i("JSON EXCEPTION: ", ">>>>>>>>>>>> " + e);
							snackAlert.putExtra("SnacksName", AppConstants.NO_RESPONSE);
							Toast.makeText(getApplicationContext(), "Please Contact Snack Support", Toast.LENGTH_SHORT).show();
						}			
						
					}else{
						 snackAlert.putExtra("SnacksName", AppConstants.NO_RESPONSE);
						 Toast.makeText(getApplicationContext(), "Please Contact Snack Support", Toast.LENGTH_SHORT).show();
					}
					startActivity(snackAlert);
				} catch (UnsupportedEncodingException e1) {
					snackAlert.putExtra("SnacksName", AppConstants.NO_RESPONSE);
					startActivity(snackAlert);
				} catch (ClientProtocolException e1) {
					snackAlert.putExtra("SnacksName", AppConstants.NO_RESPONSE);
					startActivity(snackAlert);
				} catch (IOException e1) {
					snackAlert.putExtra("SnacksName", AppConstants.NO_RESPONSE);
					startActivity(snackAlert);
				}
				
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
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	    	pDialog.dismiss();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}

	protected void cancel(boolean b) {
		// TODO Auto-generated method stub
		pDialog.dismiss();
	}
}
