package codex.ibe.nnamdi.simpletipcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/**
 * Created by Khalid on 2015-06-16.
 */
public class SecondScreen extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.second_activity);

        Intent callingActivity = getIntent();

        Double billAmount = callingActivity.getDoubleExtra("billAmount", 0.00);
        Double tipPercentage = callingActivity.getDoubleExtra("tipAmount", 0.00);
        int numberOfPayers = callingActivity.getIntExtra("numOfPayers", 0);

        TextView billAmountTV = (TextView)findViewById(R.id.billSummaryValue);
        TextView tipAmountTV = (TextView)findViewById(R.id.tipSummaryValue);
        TextView totalAmountTV = (TextView)findViewById(R.id.totalSummaryValue);

        Double tipAmount = (((tipPercentage)/ 100)*(billAmount));
        Double totalAmount = billAmount + tipAmount;


        billAmountTV.setText(billAmount.toString());
        tipAmountTV.setText(tipAmount.toString());
        totalAmountTV.setText(totalAmount.toString());

    }


}
