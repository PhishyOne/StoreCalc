package com.td.storecalc;

import android.content.*;
import android.support.v7.widget.*;
import android.text.*;
import android.util.*;
import android.widget.*;

public class CurrencyEditText extends AppCompatEditText {
    private static final int MAX_LENGTH = 5;
    private static final int MAX_DECIMAL_DIGIT = 2;
    private CurrencyTextWatcher currencyTextWatcher = new CurrencyTextWatcher(this);

    public CurrencyEditText(Context context) {
        this(context, null);
    }

    public CurrencyEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public CurrencyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        this.setFilters(new InputFilter[] { new InputFilter.LengthFilter(MAX_LENGTH) });
        this.addTextChangedListener(currencyTextWatcher);
    }

    public double getCleanDoubleValue() {
        String str = getText().toString().trim().replaceAll(",", "");
        if (TextUtils.isEmpty(str)) {
            return 0.0;
        } else {
            try {
                return Double.parseDouble(str);
            } catch (NumberFormatException e) {
                return 0.0;
            }
        }
    }

    private static class CurrencyTextWatcher implements TextWatcher {
        private final EditText editText;

        CurrencyTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // do nothing
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // do nothing
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // do nothing
        }
    }
}

