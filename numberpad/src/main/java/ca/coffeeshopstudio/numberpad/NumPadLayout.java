package ca.coffeeshopstudio.numberpad;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by terence on 1/20/2016. Copyright 2016
 */
public class NumPadLayout extends LinearLayout implements View.OnClickListener {

    private OnValueUpdateListener listener;

    private int maxFractionDigits;

    private String displayValue;
    private String signOfValue = "-";

    private BigDecimal actualValue = new BigDecimal(0);

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
        maxFractionDigits = NumberFormat.getCurrencyInstance(Locale.getDefault()).getMaximumFractionDigits();
        updateValue(0);
        buildButtons();
    }

    private void buildButtons() {
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
            if (displayValue != null && displayValue.length() > 0) {
                displayValue = displayValue.substring(0, displayValue.length() - 1);
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
        //initialize our displayValue as necessary
        if (displayValue != null && displayValue.length() > 0)
            displayValue += Integer.toString(i);
        else
            displayValue = Integer.toString(i);

        updateDisplay();
    }

    private void updateDisplay() {
        int lengthOfInput = displayValue.length();

        //if we have less digits then fraction digits, pad the left side
        if (lengthOfInput < maxFractionDigits)
        {
            int padding = maxFractionDigits - lengthOfInput;

            while (padding >= 0)
            {
                displayValue = "0" + displayValue;
                padding--;
            }

            //reget our length
            lengthOfInput = displayValue.length();
        }

        String leftOfDecimal = this.displayValue.substring(0, lengthOfInput - maxFractionDigits);
        String rightOfDecimal = this.displayValue.substring(lengthOfInput - maxFractionDigits);

        while (leftOfDecimal.startsWith("0") && leftOfDecimal.length() > 1)
            leftOfDecimal = leftOfDecimal.substring(1);

        String tempValue = signOfValue + leftOfDecimal + "." + rightOfDecimal;

        actualValue = new BigDecimal(tempValue);
        if (this.listener != null)
            listener.onUpdate(tempValue);
    }

    public BigDecimal getValue() {
        return actualValue;
    }

    public void setValue(BigDecimal newValue)
    {
        displayValue = newValue.toString();
    }

    public void setDecimalPoints(int precision) {
        maxFractionDigits = precision;
    }

    public void setOnValueListener(OnValueUpdateListener listener) {
        this.listener = listener;
    }
}
