package codex.ibe.nnamdi.simpletipcalculator;


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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    final Context context = this;
    private float serviceRating;
    private EditText billAmountET;
    private EditText tipAmountET;
    private Spinner spinner;
    private int numberOfPeoplePaying = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add filters to the editText fields
        billAmountET = (EditText)findViewById(R.id.billAmountEditText);
        billAmountET.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(5,2)});

        tipAmountET = (EditText)findViewById(R.id.tipEditText);
        tipAmountET.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(3,2)});

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.numSplitBill, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);
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
        switch (item.getItemId()) {
            case R.id.action_settings:
                //Toast.makeText(this, "ADD!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, PreferencesActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onCalculateTip(View view) {
        // Re-create widgets from MainActitiy to obtain values from

//        NumberPicker numberOfPayersP = (NumberPicker)findViewById(R.id.number_picker);
        Spinner spinner = (Spinner)findViewById(R.id.spinner);

        //Validate user inputs, then process user inputs.
        if( billAmountET.getText().toString().isEmpty() ) {
            toast("Bill amount field is blank. Please enter your bill.", Toast.LENGTH_LONG);
        } else if ( tipAmountET.getText().toString().isEmpty() ) {
            toast("Tip percentage field is blank. Please enter your tip percentage", Toast.LENGTH_SHORT);
        } else {
            // Pull values from widgets
            Double billAmount = Double.parseDouble(billAmountET.getText().toString());
            Double tipAmount = Double.parseDouble(tipAmountET.getText().toString());

            //Create and initialize our intent to move to the second screen or activity
            Intent getBalance = new Intent(this, SecondScreen.class);

            // Sending the values obtained from widgets to the second screen
            getBalance.putExtra("billAmount", billAmount);
            getBalance.putExtra("tipAmount", tipAmount);
            getBalance.putExtra("numOfPayers", numberOfPeoplePaying);

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                numberOfPeoplePaying = 1;
                break;
            case 1:
                numberOfPeoplePaying = 2;
                break;
            case 2:
                numberOfPeoplePaying = 3;
                break;
            case 3:
                numberOfPeoplePaying = 4;
                break;
            case 4:
                numberOfPeoplePaying = 5;
                break;
            case 5:
                numberOfPeoplePaying = 6;
                break;
            case 6:
                numberOfPeoplePaying = 7;
                break;
            case 7:
                numberOfPeoplePaying = 8;
                break;
            case 8:
                numberOfPeoplePaying = 9;
                break;
            case 9:
                numberOfPeoplePaying = 10;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //TODO Clean up second screen activity
        //TODO Name activities
        //TODO Fix idea button
        //TODO Implement settings functions

    }
}
