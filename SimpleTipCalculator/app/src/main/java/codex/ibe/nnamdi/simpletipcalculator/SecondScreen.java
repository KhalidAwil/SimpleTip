package codex.ibe.nnamdi.simpletipcalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Created by Khalid on 2015-06-16.
 */
public class SecondScreen extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.second_activity);

        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        Intent callingActivity = getIntent();

        Double billAmount = callingActivity.getDoubleExtra("billAmount", 0.00);
        Double tipPercentage = callingActivity.getDoubleExtra("tipAmount", 0.00);
        int numberOfPayers = callingActivity.getIntExtra("numOfPayers", 0);

        TextView billAmountTV = (TextView)findViewById(R.id.billSummaryValue);
        TextView tipAmountTV = (TextView)findViewById(R.id.tipSummaryValue);
        TextView totalAmountTV = (TextView)findViewById(R.id.totalSummaryValue);
        TextView tipPerPersonAmountTextView = (TextView)findViewById(R.id.tipPerPersonValue);
        TextView eachPersonPayTextView = (TextView)findViewById(R.id.eachPersonPayValue);
        TextView tipParameterValue = (TextView)findViewById(R.id.tipParameterValue);
        TextView peopleValue = (TextView)findViewById(R.id.peopleValue);


        Double tipAmount = (((tipPercentage)/ 100)*(billAmount));
        Double totalAmount = billAmount + tipAmount;

        Double eachPersonTip = tipAmount/numberOfPayers;
        Double eachPersonPay = totalAmount/numberOfPayers;

        tipAmount = round(tipAmount, 2);
        totalAmount = round(totalAmount, 2);
        eachPersonPay = round(eachPersonPay, 2);
        eachPersonTip = round(eachPersonTip, 2);

        billAmountTV.setText(format(billAmount));
        tipAmountTV.setText(format(tipAmount));
        totalAmountTV.setText(format(totalAmount));
        tipPerPersonAmountTextView.setText(format(eachPersonTip));
        eachPersonPayTextView.setText(format(eachPersonPay));
        setSummary(tipParameterValue, peopleValue, numberOfPayers, tipPercentage);
    }

    private void setSummary(TextView tipParameterValue, TextView peopleValue, int numberOfPayers, Double tipPercentage) {
        tipParameterValue.setText(tipPercentage + "%");
        peopleValue.setText(numberOfPayers + "");
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static String formatEuro( Double toFormat) {
        return DecimalFormat.getCurrencyInstance(Locale.GERMANY).format(toFormat);
    }

    public static String formatDollar(Double toFormat) {
        return DecimalFormat.getCurrencyInstance(Locale.CANADA).format(toFormat);
    }

    public static String formatPound(Double toFormat) {
        return DecimalFormat.getCurrencyInstance(Locale.UK).format(toFormat);
    }

    public String format(Double toFormat) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String preference = sharedPreferences.getString("currency","1");
        if (preference.equals("1") ) {
            return formatDollar(toFormat);
        } else if (preference.equals("2") ) {
            return formatEuro(toFormat);
        } else if (preference.equals("3") ) {
            return formatPound(toFormat);
        } else {
            return formatDollar(toFormat);
        }
    }

    public void onClickDone(View view) {
        finish();
    }

}
