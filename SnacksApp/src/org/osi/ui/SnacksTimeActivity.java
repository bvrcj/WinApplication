package org.osi.ui;

import org.osi.constants.AppConstants;
import org.osi.snacksapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 
 * @author vbommaraju
 *
 */
public class SnacksTimeActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.snacks_layout);
		//Toast.makeText(getApplicationContext(), AppConstants.RESPONSE_SNACKS_NAME, Toast.LENGTH_LONG).show();
		
		Intent intent = getIntent();
		String snackName = intent.getStringExtra("SnacksName");
		String snackDate = intent.getStringExtra("SnacksDate");
		//String snackCampus = intent.getStringExtra("SnacksCampus");
		TextView snackView = (TextView)findViewById(R.id.snackname);
		TextView dateView = (TextView)findViewById(R.id.snack_date);
		if(snackName.equals(null)||snackName==null){
			RelativeLayout relativeLayout=(RelativeLayout) findViewById(R.id.snackImage);
			relativeLayout.setBackgroundResource(R.drawable.no_response);   
			dateView.setText(AppConstants.OSI_SNACK_ALERT);
			snackView.setText("");
		}else
		if(snackName.equals(AppConstants.NO_RESPONSE)){
			RelativeLayout relativeLayout=(RelativeLayout) findViewById(R.id.snackImage);
			relativeLayout.setBackgroundResource(R.drawable.no_response);   
			dateView.setText(AppConstants.OSI_SNACK_ALERT);
			snackView.setText(AppConstants.NO_RESPONSE);
		}else
		if(snackName.equals(AppConstants.NETWORK_PROBLEM)){
			RelativeLayout relativeLayout=(RelativeLayout) findViewById(R.id.snackImage);
			relativeLayout.setBackgroundResource(R.drawable.no_response);   
			dateView.setText(AppConstants.OSI_SNACK_ALERT);
			snackView.setText(AppConstants.NETWORK_PROBLEM);
		}else{
			setBackground(snackName);
			snackView.setText( snackName);
			dateView.setText(snackDate);
		}
	}
	
	public void closeSnacks(View clickedButton) {
        this.finish();
    }
	
	public void setBackground(String snackName){
		RelativeLayout relativeLayout=(RelativeLayout) findViewById(R.id.snackImage);
		relativeLayout.setBackgroundResource(R.drawable.snackstime);
		
		if(snackName.equals(AppConstants.IDLY_SAMBAR)){
			relativeLayout.setBackgroundResource(R.drawable.idlisambar);    
		}
		if(snackName.equals(AppConstants.PURI)){
			relativeLayout.setBackgroundResource(R.drawable.puri);    
		}
		if(snackName.equals(AppConstants.VADA_PAAV)){
			relativeLayout.setBackgroundResource(R.drawable.vadapaav);    
		}
		if(snackName.equals(AppConstants.PAANI_PURI)){
			relativeLayout.setBackgroundResource(R.drawable.paanipuri);    
		}
		if(snackName.equals(AppConstants.PESARATTU_UPMA)){
			relativeLayout.setBackgroundResource(R.drawable.pesarattu_upama);    
		}
		if(snackName.equals(AppConstants.MYSORE_BHAJJI)){
			relativeLayout.setBackgroundResource(R.drawable.mysorebhajji);    
		}
		if(snackName.equals(AppConstants.MASALA_DOSA)){
			relativeLayout.setBackgroundResource(R.drawable.masaladosa);    
		}
		if(snackName.equals(AppConstants.VEG_MANCHURIA) || snackName.equals(AppConstants.VEG_MANCHURIA_1)){
			relativeLayout.setBackgroundResource(R.drawable.vegmanchuria);    
		}
		if(snackName.equals(AppConstants.PAAV_BHAJJI)){
			relativeLayout.setBackgroundResource(R.drawable.paavbhaji);    
		}
		if(snackName.equals(AppConstants.CHILLY_GARI_SAMBAR)){
			relativeLayout.setBackgroundResource(R.drawable.vadasambar);    
		}
		if(snackName.equals(AppConstants.ONION_UTTAPAM)){
			relativeLayout.setBackgroundResource(R.drawable.onionuttapam);    
		}
		if(snackName.equals(AppConstants.VEG_CHEESE_SANDWICH)){
			relativeLayout.setBackgroundResource(R.drawable.vegcheesesandwich);    
		}
		if(snackName.equals(AppConstants.CHAAT)){
			relativeLayout.setBackgroundResource(R.drawable.chaat);    
		}
		if(snackName.equals(AppConstants.WADA_SAMBAR)){
			relativeLayout.setBackgroundResource(R.drawable.vadasambar);    
		}
		if(snackName.equals(AppConstants.VEG_EGG_NOODLES)){
			relativeLayout.setBackgroundResource(R.drawable.noodles);    
		}
		if(snackName.equals(AppConstants.PUNUGULU)){
			relativeLayout.setBackgroundResource(R.drawable.punugulu);    
		}
		if(snackName.equals(AppConstants.ONION_DOSA)){
			relativeLayout.setBackgroundResource(R.drawable.oniondosa);    
		}
		if(snackName.equals(AppConstants.ONION_PAKODA)){
			relativeLayout.setBackgroundResource(R.drawable.pakodi);    
		}
		if(snackName.equals(AppConstants.BHEL_PURI)){
			relativeLayout.setBackgroundResource(R.drawable.bhelpuri);    
		}
		if(snackName.equals(AppConstants.TOMATO_BATH)){
			relativeLayout.setBackgroundResource(R.drawable.tomatobath);    
		}
	}
}
