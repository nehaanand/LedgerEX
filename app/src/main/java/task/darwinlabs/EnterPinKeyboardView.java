package task.darwinlabs;

import android.content.Context;
import android.support.annotation.IdRes;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by SS0088 on 9/2/2017.
 */
public class EnterPinKeyboardView extends FrameLayout implements View.OnClickListener {

    private EditText mPasswordField;
    private EditText pinval1, pinval2, pinval3, pinval4;
    private int textcount;

    public EnterPinKeyboardView(Context context) {
        super(context);
        init();
    }

    public EnterPinKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EnterPinKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.keyboard, this);
        initViews();
    }

    private void initViews() {
        pinval1 = $(R.id.etdigit1);
        pinval2 = $(R.id.etdigit2);
        pinval3 = $(R.id.etdigit3);
        pinval4 = $(R.id.etdigit4);

        $(R.id.t9_key_0).setOnClickListener(this);
        $(R.id.t9_key_1).setOnClickListener(this);
        $(R.id.t9_key_2).setOnClickListener(this);
        $(R.id.t9_key_3).setOnClickListener(this);
        $(R.id.t9_key_4).setOnClickListener(this);
        $(R.id.t9_key_5).setOnClickListener(this);
        $(R.id.t9_key_6).setOnClickListener(this);
        $(R.id.t9_key_7).setOnClickListener(this);
        $(R.id.t9_key_8).setOnClickListener(this);
        $(R.id.t9_key_9).setOnClickListener(this);
        $(R.id.t9_key_backspace).setOnClickListener(this);
        $(R.id.t9_key_dot).setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        // handle number button click
        if (v.getTag() != null && "number_button".equals(v.getTag())) {
//            pinval1.append(((TextView) v).getText());
            return;
        }

        switch (v.getId()) {
            case R.id.t9_key_dot: {

                
            }
            break;
            case R.id.t9_key_backspace: { // handle backspace button
//                 delete one character
//                Editable editable = pinval1.getText();
//                int charCount = editable.length();
//                if (charCount > 0) {
//                    editable.delete(charCount - 1, charCount);
//                }
            }
            break;
        }
    }

    public String getInputText() {
        return mPasswordField.getText().toString();
    }

    protected <T extends View> T $(@IdRes int id) {
        return (T) super.findViewById(id);
    }
}