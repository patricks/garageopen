package at.helmsdeep.garageopen;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingsActivity extends PreferenceActivity {
	public static final String KEY_PREF_PHONENUMBER = "pref_phonenumber";
	public static final String KEY_PREF_SMSTEXT = "pref_smstext";
	public static final String KEY_PREF_VERSION = "pref_version";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Display the fragment as the main content.
		getFragmentManager().beginTransaction()
				.replace(android.R.id.content, new SettingsFragment()).commit();
	}
}
