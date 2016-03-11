package org.osi.receiver;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.osi.constants.AppConstants;
import org.osi.service.SnackService;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;

/**
 * 
 * @author vbommaraju
 * 
 */
public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {

	final public static String ONE_TIME = "onetime";

	@Override
	public void onReceive(Context context, Intent intent) {
		PowerManager pm = (PowerManager) context
				.getSystemService(Context.POWER_SERVICE);
		PowerManager.WakeLock wl = pm.newWakeLock(
				PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");
		// Acquire the lock
		wl.acquire();

		// You can do the processing here.
		Bundle extras = intent.getExtras();
		StringBuilder msgStr = new StringBuilder();

		if (extras != null && extras.getBoolean(ONE_TIME, Boolean.FALSE)) {
			// Make sure this intent has been sent by the one-time timer button.
			msgStr.append("One time Timer : ");
		}
		Format formatter = new SimpleDateFormat("hh:mm:ss a");
		msgStr.append(formatter.format(new Date()));

		// Toast.makeText(context, msgStr, Toast.LENGTH_LONG).show();

		// Release the lock
		wl.release();

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
			Intent i = new Intent(context, SnackService.class);
			context.startService(i);
		}
	}

	public void SetAlarm(Context context) {
		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
		intent.putExtra(ONE_TIME, Boolean.FALSE);
		
		Calendar calendar = Calendar.getInstance();
		// 4:59 PM 
		calendar.set(Calendar.HOUR_OF_DAY, 16);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 0);
		
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
		// After after 5 seconds
		/*am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
				1000 * 60, pi);*/
		
		// After after every day at 4:59 PM 
		am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);
	}

	public void CancelAlarm(Context context) {
		Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
		PendingIntent sender = PendingIntent
				.getBroadcast(context, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(sender);
	}

	public void setOnetimeTimer(Context context) {
		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
		intent.putExtra(ONE_TIME, Boolean.TRUE);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
		am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);
	}

}