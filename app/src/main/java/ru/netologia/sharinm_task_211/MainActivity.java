package ru.netologia.sharinm_task_211;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class MainActivity extends AppCompatActivity {

    private EditText mInputMoney;
    private EditText mInputInfo;
    private Button mBtnOk;
    private CheckBox mBankCardChkBx;
    private CheckBox mMobilePhoneChkBx;
    private CheckBox mCashAddressChkBx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mInputMoney = findViewById(R.id.inputMoney);
        mInputInfo = findViewById(R.id.inputInfo);
        mBtnOk = findViewById(R.id.btnOK);
        mBankCardChkBx = findViewById(R.id.bankCardChkBx);
        mMobilePhoneChkBx = findViewById(R.id.mobilePhoneChkBx);
        mCashAddressChkBx = findViewById(R.id.cashAddressChkBx);
        mBankCardChkBx.setOnCheckedChangeListener(checkedChangeListener);
        mMobilePhoneChkBx.setOnCheckedChangeListener(checkedChangeListener);
        mCashAddressChkBx.setOnCheckedChangeListener(checkedChangeListener);
        mBtnOk.setOnClickListener(new CompoundButton.OnClickListener() {
            @Override
            public void onClick(View v) {

                float summaFloat = 0;
                String textString = "";

                try {
                    summaFloat = Float.parseFloat(mInputMoney.getText().toString());
                } catch (Exception exp) {
                    Toast.makeText(MainActivity.this, "Сумма для оплаты не введена", Toast.LENGTH_LONG).show();
                }

                if ((mBankCardChkBx.isChecked() == false) && (mMobilePhoneChkBx.isChecked() == false) && (mCashAddressChkBx.isChecked() == false)) {
                    Toast.makeText(MainActivity.this, "Не выбран вариант оплаты!", Toast.LENGTH_LONG).show();
                } else {
                    if (mBankCardChkBx.isChecked()) {
                        textString = " будет осуществлена с карты";
                    }

                    if (mMobilePhoneChkBx.isChecked()) {
                        textString = " будет осуществена с мобильного телефона";
                    }

                    if (mCashAddressChkBx.isChecked()) {
                        textString = " с помощью налички при получении";
                    }
                    Toast.makeText(MainActivity.this, "Оплата  " + Float.parseFloat(String.valueOf(summaFloat)) + textString, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void resetCheckBoxes(){
        mBankCardChkBx.setChecked(false);
        mMobilePhoneChkBx.setChecked(false);
        mCashAddressChkBx.setChecked(false);
    }

    CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) {
                switch (compoundButton.getId()) {
                    case R.id.bankCardChkBx:
                        resetCheckBoxes();
                        mBankCardChkBx.setChecked(true);
                        mInputInfo.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                    case R.id.mobilePhoneChkBx:
                        resetCheckBoxes();
                        mMobilePhoneChkBx.setChecked(true);
                        mInputInfo.setInputType(InputType.TYPE_CLASS_PHONE);
                        break;
                    case R.id.cashAddressChkBx:
                        resetCheckBoxes();
                        mInputInfo.setInputType(InputType.TYPE_CLASS_TEXT);
                        mCashAddressChkBx.setChecked(true);
                        break;
                    default:
                }
            }
        }
    };
}
