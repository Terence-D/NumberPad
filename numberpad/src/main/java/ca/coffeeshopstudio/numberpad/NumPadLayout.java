package ca.coffeeshopstudio.numberpad;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by terence on 1/20/2016. Copyright 2016
 */
public class NumPadLayout extends LinearLayout implements View.OnClickListener {

    private NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
    private int maxFractionDigits;

    private String value;
    private String signOfValue = "-";

    public NumPadLayout(Context context) {
        super(context);
        initializeViews(context);
    }

    public NumPadLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public NumPadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context);
    }

    /**
     * Inflates the views in the layout.
     *
     * @param context the current context for the view.
     */
    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.numpad, this);
        //set our decimal value
        maxFractionDigits = format.getMaximumFractionDigits();
        updateValue(0);
        buildButtons();
    }

    protected void buildButtons() {
        findViewById(R.id.btnDigit0).setOnClickListener(this);
        findViewById(R.id.btnDigit1).setOnClickListener(this);
        findViewById(R.id.btnDigit2).setOnClickListener(this);
        findViewById(R.id.btnDigit3).setOnClickListener(this);
        findViewById(R.id.btnDigit4).setOnClickListener(this);
        findViewById(R.id.btnDigit5).setOnClickListener(this);
        findViewById(R.id.btnDigit6).setOnClickListener(this);
        findViewById(R.id.btnDigit7).setOnClickListener(this);
        findViewById(R.id.btnDigit8).setOnClickListener(this);
        findViewById(R.id.btnDigit9).setOnClickListener(this);
        findViewById(R.id.btnDigitInvert).setOnClickListener(this);
        findViewById(R.id.btnDigitBackspace).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //can't use switches in Libraries with Resources :(
        int id = v.getId();
        if (id == R.id.btnDigitBackspace) {
            if (value != null && value.length() > 0) {
                value = value.substring(0, value.length() - 1);
                updateDisplay();
            }
        }
        if (id == R.id.btnDigit0) {
            updateValue(0);
        }
        if (id == R.id.btnDigit1) {
            updateValue(1);
        }
        if (id == R.id.btnDigit2) {
            updateValue(2);
        }
        if (id == R.id.btnDigit3) {
            updateValue(3);
        }
        if (id == R.id.btnDigit4) {
            updateValue(4);
        }
        if (id == R.id.btnDigit5) {
            updateValue(5);
        }
        if (id == R.id.btnDigit6) {
            updateValue(6);
        }
        if (id == R.id.btnDigit7) {
            updateValue(7);
        }
        if (id == R.id.btnDigit8) {
            updateValue(8);
        }
        if (id == R.id.btnDigit9) {
            updateValue(9);
        }
        if (id == R.id.btnDigitInvert) {
            if (signOfValue.length() == 0) //positive
                signOfValue = "-";
            else
                signOfValue = "";
            updateDisplay();
        }
    }

    private void updateValue(int i) {
        //initialize our value as necessary
        if (value != null && value.length() > 0)
            value += Integer.toString(i);
        else
            value = Integer.toString(i);

        updateDisplay();
    }

    private void updateDisplay() {
        TextView txtValue = (TextView) findViewById(R.id.txtValue);

        if (signOfValue.length() == 0) {
            txtValue.setTextColor(Color.BLUE);
        }
        else {
            txtValue.setTextColor(Color.RED);
        }

        int lengthOfInput = value.length();

        //if we have less digits then fraction digits, pad the left side
        if (lengthOfInput < maxFractionDigits)
        {
            int padding = maxFractionDigits - lengthOfInput;
            while (padding >= 0)
            {
                value = "0" + value;
                padding--;
            }

            //reget our length
            lengthOfInput = value.length();

            //now insert a decimal character
        }

        String displayValue = signOfValue + value.substring(0, lengthOfInput - maxFractionDigits) + "." + value.substring(lengthOfInput - maxFractionDigits);

        BigDecimal currencyValue = new BigDecimal(displayValue);
        txtValue.setText(currencyValue.toString());
    }

    public BigDecimal getValue() {
        return new BigDecimal(value);
    }

    public void setValue(BigDecimal newValue)
    {
        value = newValue.toString();
    }

    public void setDecimalPoints(int precision) {
        maxFractionDigits = precision;
    }
}
