package codex.ibe.nnamdi.simpletipcalculator;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Khalid on 2015-06-17.
 */
public class PreferencesActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new Preferences()).commit();
    }
}
