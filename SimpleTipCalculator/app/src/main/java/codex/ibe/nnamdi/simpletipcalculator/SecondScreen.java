package codex.ibe.nnamdi.simpletipcalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Khalid on 2015-06-16.
 */
public class SecondScreen extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.second_activity);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String currency = settings.getString("currency", "1");
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

        Double tipAmount = (((tipPercentage)/ 100)*(billAmount));
        Double totalAmount = billAmount + tipAmount;

        Double eachPersonTip = tipAmount/numberOfPayers;
        Double eachPersonPay = totalAmount/numberOfPayers;

        tipAmount = round(tipAmount, 2);
        totalAmount = round(totalAmount, 2);
        eachPersonPay = round(eachPersonPay, 2);
        eachPersonTip = round(eachPersonTip, 2);

        StringBuilder billTemp = new StringBuilder(billAmount.toString());
        billTemp.insert(0,currencyConverter(currency));
        String billFinal = billTemp.toString();

        StringBuilder tipTemp = new StringBuilder(tipAmount.toString());
        tipTemp.insert(0, currencyConverter(currency));
        String tipFinal = tipTemp.toString();

        StringBuilder totalTemp = new StringBuilder(totalAmount.toString());
        totalTemp.insert(0,currencyConverter(currency));
        String totalFinal = totalTemp.toString();

        StringBuilder eachTipStringBuilder = new StringBuilder(eachPersonTip.toString());
        eachTipStringBuilder.insert(0, "$ ");
        String eachTipfinal = eachTipStringBuilder.toString();

        StringBuilder eachPayStringBuilder = new StringBuilder(eachPersonPay.toString());
        eachPayStringBuilder.insert(0, "$ ");
        String eachPayfinal = eachPayStringBuilder.toString();


        billAmountTV.setText(billFinal.toString());
        tipAmountTV.setText(tipFinal.toString());
        totalAmountTV.setText(totalFinal.toString());
        tipPerPersonAmountTextView.setText(eachTipfinal);
        eachPersonPayTextView.setText(eachPayfinal);
    }

    public String currencyConverter(String x) {
        String y = "";

        switch (x) {
            case "1":
                y = "$ ";
            case "2":
                y = "€ ";
            case "3":
                y = "£ ";
        }
        return y;
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


}
