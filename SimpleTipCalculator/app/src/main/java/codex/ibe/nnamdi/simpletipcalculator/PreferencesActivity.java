package codex.ibe.nnamdi.simpletipcalculator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

/**
 * Created by Khalid on 2015-06-17.
 */
public class PreferencesActivity extends PreferenceActivity {

    protected Preferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = new Preferences();
        getFragmentManager().beginTransaction().replace(android.R.id.content, preferences).commit();
    }

//    @Override
//    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//            String value = sharedPreferences.getString(key, "");
//            preferences.findPreference(key).setSummary(value);
//    }
}
