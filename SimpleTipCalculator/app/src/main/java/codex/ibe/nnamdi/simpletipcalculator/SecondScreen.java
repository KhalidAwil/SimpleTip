package codex.ibe.nnamdi.simpletipcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.WindowManager;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Khalid on 2015-06-16.
 */
public class SecondScreen extends Activity {
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

        Double tipAmount = (((tipPercentage)/ 100)*(billAmount));
        Double totalAmount = billAmount + tipAmount;

        tipAmount = round(tipAmount, 2);
        totalAmount = round(totalAmount, 2);

        StringBuilder billTemp = new StringBuilder(billAmount.toString());
        billTemp.insert(0,"$ ");
        String billFinal = billTemp.toString();

        StringBuilder tipTemp = new StringBuilder(tipAmount.toString());
        tipTemp.insert(0, "$ ");
        String tipFinal = tipTemp.toString();

        StringBuilder totalTemp = new StringBuilder(totalAmount.toString());
        totalTemp.insert(0, "$ ");
        String totalFinal = totalTemp.toString();


        billAmountTV.setText(billFinal.toString());
        tipAmountTV.setText(tipFinal.toString());
        totalAmountTV.setText(totalFinal.toString());
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


}
