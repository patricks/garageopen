package at.helmsdeep.garageopen;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final int RESULT_SETTINGS = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final ImageButton openBtn = (ImageButton) findViewById(R.id.btnOpen);
		openBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(),
						SMSReceiver.class);
				sendBroadcast(intent);
			}
		});

		showUserSettings();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivityForResult(intent, RESULT_SETTINGS);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case RESULT_SETTINGS:
			showUserSettings();
			break;

		default:
			break;
		}
	}

	private void showUserSettings() {
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(this);

		String phoneNumber = sharedPrefs.getString(
				SettingsActivity.KEY_PREF_PHONENUMBER, "");
		String smsMessage = sharedPrefs.getString(
				SettingsActivity.KEY_PREF_SMSTEXT, "");

		TextView phoneNumberTextView = (TextView) findViewById(R.id.lbl_phonenumber);
		String phnNbr = getApplicationContext().getResources().getString(
				R.string.lbl_phonenumber);
		phoneNumberTextView.setText(phnNbr + ": " + phoneNumber);

		TextView smsMessageTextView = (TextView) findViewById(R.id.lbl_sms_message);
		String msg = getApplicationContext().getResources().getString(
				R.string.lbl_sms_message);
		smsMessageTextView.setText(msg + ": " + smsMessage);
	}
}
