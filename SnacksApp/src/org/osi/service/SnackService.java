package org.osi.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;
import org.osi.constants.AppConstants;
import org.osi.network.ServiceHandler;
import org.osi.ui.SnacksTimeActivity;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * 
 * @author vbommaraju
 *
 */
public class SnackService extends IntentService {

	String resposeMsg;
	public SnackService() {
		 super("SnacksWebRequestService");
	}

	@Override
	public void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onCreate();
		ServiceHandler sh = new ServiceHandler();		
		//String day ="3-2-2016 - Wednesday";
		String day=AppConstants.DAY;
		day=day.replace(" ", "%20");
		//Toast.makeText(getBaseContext(), AppConstants.DAY, Toast.LENGTH_LONG).show();
		Intent snackAlert= new Intent(this, SnacksTimeActivity.class);
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
	
}
