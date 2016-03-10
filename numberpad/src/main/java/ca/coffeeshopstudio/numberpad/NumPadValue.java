package ca.coffeeshopstudio.numberpad;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;

/**
 * Created by terence on 3/7/2016. Copyright 2016
 */
public class NumPadValue {
    public boolean isPositive = false;
    private int precision;
    private String value;
    private int rounding = BigDecimal.ROUND_HALF_UP;

    public NumPadValue() {
        precision = NumberFormat.getCurrencyInstance(Locale.getDefault()).getMaximumFractionDigits();
        padValue("0");
    }

    public void setValue(String newValue) {
        BigDecimal tempValue = new BigDecimal(newValue);
        String t = tempValue.setScale(precision, rounding).toPlainString();
        String temp = t.replaceAll("\\.", "");
        padValue(temp);
    }

    public BigDecimal getValue() {
        String temp = getNumericValue();
        if (!isPositive)
            temp = "-" + temp;
        return new BigDecimal(temp);
    }

    public void setValue(BigDecimal newValue) {
        isPositive = newValue.signum() != -1;
        setValue(newValue.abs().toPlainString());
    }

    public String toString() {
        return getValue().toPlainString();
    }

    public void append(int newValue) {
        value += newValue;
    }

    public void trim() {
        if (value.length() > precision)
            value = value.substring(0, value.length() - 1);
    }

    public void setPrecision(int precision) {
        this.precision = precision;
        while (value.length() < precision)
            value += "0";
    }

    public void setRounding(int rounding) {
        this.rounding = rounding;
    }

    private String getNumericValue() {
        System.out.println(value);
        String left = value.substring(0, value.length() - precision);
        String right = value.substring(value.length() - precision);
        return left + "." + right;
    }

    private void padValue(String newValue) {
        char[] chars = new char[precision];
        Arrays.fill(chars, '0');
        value = new String(chars) + newValue;
    }
}
