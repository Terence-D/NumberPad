package ca.coffeeshopstudio.numberpad;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.math.BigDecimal;

/**
 * Created by terence on 1/20/2016. Copyright 2016
 */
public class NumPadLayout extends LinearLayout implements View.OnClickListener {

    NumPadValue npv = new NumPadValue();
    private OnValueUpdateListener listener;

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
            npv.trim();
        }
        if (id == R.id.btnDigit0) {
            npv.append(0);
        }
        if (id == R.id.btnDigit1) {
            npv.append(1);
        }
        if (id == R.id.btnDigit2) {
            npv.append(2);
        }
        if (id == R.id.btnDigit3) {
            npv.append(3);
        }
        if (id == R.id.btnDigit4) {
            npv.append(4);
        }
        if (id == R.id.btnDigit5) {
            npv.append(5);
        }
        if (id == R.id.btnDigit6) {
            npv.append(6);
        }
        if (id == R.id.btnDigit7) {
            npv.append(7);
        }
        if (id == R.id.btnDigit8) {
            npv.append(8);
        }
        if (id == R.id.btnDigit9) {
            npv.append(9);
        }
        if (id == R.id.btnDigitInvert) {
            npv.isPositive = !npv.isPositive;
        }
        updateDisplay();
    }

    private void updateDisplay() {
        if (this.listener != null)
            listener.onUpdate(npv.toString());
    }

    public BigDecimal getValue() {
        return npv.getValue();
    }

    public void setValue(BigDecimal newValue)
    {
        npv.setValue(newValue);
        updateDisplay();
    }

    public void setPrecision(int precision) {
        npv.setPrecision(precision);
    }

    public void setRoundingMode(int roundingMode) {
        npv.setPrecision(roundingMode);
    }

    public void setOnValueListener(OnValueUpdateListener listener) {
        this.listener = listener;
    }
}
