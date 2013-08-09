package at.helmsdeep.garageopen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);

		String phoneNumber = sharedPreferences.getString(
				SettingsActivity.KEY_PREF_PHONENUMBER, "");
		String smsText = sharedPreferences.getString(
				SettingsActivity.KEY_PREF_SMSTEXT, "");

		if (phoneNumber.isEmpty()) {
			Toast.makeText(
					context,
					context.getResources().getString(
							R.string.error_no_phonenumber), Toast.LENGTH_LONG)
					.show();
			return;
		}

		if (smsText.isEmpty()) {
			Toast.makeText(
					context,
					context.getResources().getString(R.string.error_no_message),
					Toast.LENGTH_LONG).show();
			return;
		}

		Toast.makeText(context,
				context.getResources().getString(R.string.info_sending_sms),
				Toast.LENGTH_SHORT).show();
		sendSMS(phoneNumber, smsText);
	}

	private void sendSMS(String number, String message) {
		/*
		 * infos
		 * http://stackoverflow.com/questions/14452808/sending-and-receiving
		 * -sms-and-mms-in-android
		 * 
		 * http://stackoverflow.com/questions/8578689/sending-text-messages-
		 * programmatically-in-android
		 */

		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(number, null, message, null, null);
	}
}
