package codex.ibe.nnamdi.simpletipcalculator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Rating;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.IllegalFormatCodePointException;


public class MainActivity extends ActionBarActivity {

    final Context context = this;
    private float serviceRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NumberPicker np= (NumberPicker) findViewById(R.id.number_picker);
        np.setMaxValue(10);
        np.setMinValue(1);
        np.setWrapSelectorWheel(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickHelpTip(View view) {
        LayoutInflater inflater = getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LinearLayout dialogLayout = (LinearLayout) inflater.inflate(R.layout.tip_helper_dialog, null);
        builder.setTitle("Want a tip suggestion?");

        builder.setView(dialogLayout)
                .setPositiveButton(R.string.rateRateString, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        float tipPercentage = getTipPercentage(getServiceRating());
                        EditText editText = (EditText) findViewById(R.id.tipEditText);
                        editText.setText(tipPercentage + "");
                    }
                })
                .setNegativeButton(R.string.rateCancelString, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        AlertDialog alertDialog = builder.create();
        RatingBar ratingBar = (RatingBar) dialogLayout.findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                setServiceRating(rating);
            }
        });
        alertDialog.show();
    }

    private void setServiceRating(float  rental ){
        serviceRating = rental;
    }

    private float getServiceRating() {
        return serviceRating;
    }

    private float getTipPercentage(float rating) {
        float tipPercentage = 10 + (rating * 2);
        Toast.makeText(this, "We suggest you tip " +
                "" + tipPercentage + " based on your rating.", Toast.LENGTH_LONG).show();
        return tipPercentage;
    }
}
