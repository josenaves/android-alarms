package com.josenaves.alarm;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

	private final Intent intent = new Intent("com.josenaves.alarm.ALARM_FIRED");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.i("alarm", "Creating alarm");
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		cal.add(Calendar.SECOND, 3);

		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 5000, getPendingIntent());
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.cancel(getPendingIntent());
	}
	
	private PendingIntent getPendingIntent(){
		return PendingIntent.getBroadcast(this, 0, intent, 0);
	}
	
}
