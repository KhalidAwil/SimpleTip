package codex.ibe.nnamdi.simpletipcalculator;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nnamdi on 15-06-17.
 */
public class DecimalDigitsInputFilter implements InputFilter {

    Pattern pattern;

    /**
     *
     * @param beforeDecimal Number of digits allowed before the decimal point
     * @param afterDecimal Number of digits allowed after the decimal point
     */
    public DecimalDigitsInputFilter(int beforeDecimal, int afterDecimal) {
        pattern = pattern.compile( "^\\d{1," + beforeDecimal + "}(\\.\\d{0," + afterDecimal + "})?$");
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        CharSequence match = TextUtils.concat(dest.subSequence(0, dstart), source.subSequence(start, end), dest.subSequence(dend, dest.length()));
        Matcher matcher=pattern.matcher(match);
        if( !matcher.matches() )
            return "";
        return null;
    }

}
