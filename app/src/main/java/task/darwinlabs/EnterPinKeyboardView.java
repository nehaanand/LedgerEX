package task.darwinlabs;

import android.content.Context;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

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
        inflate(getContext(), R.layout.enterpinactivityskeyboard, this);
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
        pinval1 = (EditText)findViewById(R.id.etdigit1);
        if (v.getTag() != null && "number_button".equals(v.getTag()) ) {

            if(pinval1.getText().toString().equals("") && textcount==0)
            {
                pinval1.setText(((TextView) v).getText());
                textcount=textcount+1;
            }
            else  if(pinval2.getText().toString().equals("") && textcount==1)
            {
                pinval2.setText(((TextView) v).getText());
                textcount=textcount+1;
            }
            else  if(pinval3.getText().toString().equals("") && textcount==2)
            {
                pinval3.setText(((TextView) v).getText());
                textcount=textcount+1;
            }
            else  if(pinval4.getText().toString().equals("") && textcount==3)
            {
                pinval4.setText(((TextView) v).getText());
                textcount=textcount+1;
            }
            return;
        }

        switch (v.getId()) {
            case R.id.t9_key_dot: {
                if(pinval1.getText().toString().equals("") && textcount==0)
                {
                    pinval1.setText(((TextView) v).getText());
                    textcount=textcount+1;
                }
                else  if(pinval2.getText().toString().equals("") && textcount==1)
                {
                    pinval2.setText(((TextView) v).getText());
                    textcount=textcount+1;
                }
                else  if(pinval3.getText().toString().equals("") && textcount==2)
                {
                    pinval3.setText(((TextView) v).getText());
                    textcount=textcount+1;
                }
                else  if(pinval4.getText().toString().equals("") && textcount==3)
                {
                    pinval4.setText(((TextView) v).getText());
                }
            }
            break;
            case R.id.t9_key_backspace: { // handle backspace button
//                 delete one character
               if(!pinval4.getText().toString().equals("") && textcount==4)
               {
                   pinval4.setText("");
                   textcount=textcount-1;
               }
               else if(!pinval3.getText().toString().equals("") && textcount==3)
                {
                    pinval3.setText("");
                    textcount=textcount-1;
                }
              else if(!pinval2.getText().toString().equals("") && textcount==2)
                {
                    pinval2.setText("");
                    textcount=textcount-1;
                }
              else if(!pinval1.getText().toString().equals("") && textcount==1)
                {
                    pinval1.setText("");
                    textcount=textcount-1;
                }

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