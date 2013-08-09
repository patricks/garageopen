package at.helmsdeep.garageopen;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;

public class SettingsFragment extends PreferenceFragment implements
		OnSharedPreferenceChangeListener {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.settings);

		SharedPreferences sp = getPreferenceScreen().getSharedPreferences();

		EditTextPreference editTextPrefPhone = (EditTextPreference) findPreference(SettingsActivity.KEY_PREF_PHONENUMBER);
		editTextPrefPhone.setSummary(sp.getString(
				SettingsActivity.KEY_PREF_PHONENUMBER, ""));

		EditTextPreference editTextPrefMessage = (EditTextPreference) findPreference(SettingsActivity.KEY_PREF_SMSTEXT);
		editTextPrefMessage.setSummary(sp.getString(
				SettingsActivity.KEY_PREF_SMSTEXT, ""));

		String version = "";

		try {
			PackageManager manager = getActivity().getPackageManager();
			PackageInfo info = manager.getPackageInfo(getActivity()
					.getPackageName(), 0);
			version = info.versionName;
		} catch (Exception e) {
			Log.e("ERROR", "Error getting version");
		}

		Preference prefVersion = (Preference) findPreference(SettingsActivity.KEY_PREF_VERSION);
		prefVersion.setSummary(version);
	}

	@Override
	public void onResume() {
		super.onResume();
		getPreferenceScreen().getSharedPreferences()
				.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		getPreferenceScreen().getSharedPreferences()
				.unregisterOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		Preference pref = findPreference(key);
		if (pref instanceof EditTextPreference) {
			EditTextPreference etp = (EditTextPreference) pref;
			pref.setSummary(etp.getText());
		}

	}
}
