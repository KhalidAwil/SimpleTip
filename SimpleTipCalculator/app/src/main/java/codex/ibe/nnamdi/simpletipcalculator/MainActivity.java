package codex.ibe.nnamdi.simpletipcalculator;


import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    final Context context = this;
    private float serviceRating;
    EditText billAmountET;
    EditText tipAmountET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add filters to the editText fields
        billAmountET = (EditText)findViewById(R.id.billAmountEditText);
        billAmountET.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(5,2)});

        tipAmountET = (EditText)findViewById(R.id.tipEditText);
        tipAmountET.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(3,2)});

        //Set the number picker values.
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

    public void onCalculateTip(View view) {
        // Re-create widgets from MainActitiy to obtain values from

        NumberPicker numberOfPayersP = (NumberPicker)findViewById(R.id.number_picker);

        //Validate user inputs, then process user inputs.
        if( billAmountET.getText().toString().isEmpty() ) {
            toast("Bill amount field is blank. Please enter your bill.", Toast.LENGTH_LONG);
        } else if ( tipAmountET.getText().toString().isEmpty() ) {
            toast("Tip percentage field is blank. Please enter your tip percentage", Toast.LENGTH_SHORT);
        } else {
            // Pull values from widgets
            Double billAmount = Double.parseDouble(billAmountET.getText().toString());
            Double tipAmount = Double.parseDouble(tipAmountET.getText().toString());
            int numberOfPayers = numberOfPayersP.getValue();

            //Create and initialize our intent to move to the second screen or activity
            Intent getBalance = new Intent(this, SecondScreen.class);

            final int result = 1;

            // Sending the values obtained from widgets to the second screen
            getBalance.putExtra("billAmount", billAmount);
            getBalance.putExtra("tipAmount", tipAmount);
            getBalance.putExtra("numberOfPayers", numberOfPayers);

            // Start our intent
            startActivity(getBalance);
            }
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
        toast("We suggest you tip " +
                "" + tipPercentage + " based on your rating.", Toast.LENGTH_LONG);
        return tipPercentage;
    }

    private void toast( CharSequence text, int duration ) {
        Toast.makeText(this, text, duration).show();
    }
}
