package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textHello, textInfo, textCurrencyInput, textCurrencyOutput, textOutput, textHistory;
    private ImageView imageView;
    private Spinner spinnerCurrencyInput, spinnerCurrencyOutput;
    private EditText eTextNumber;
    private String[] historyArray;
    private static final int MAX_DIGIT_VALUE = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        spinnerCurrencyInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, spinnerCurrencyInput.getSelectedItem().toString()+" Selected", Toast.LENGTH_SHORT).show();
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerCurrencyOutput.setSelection(1);
        spinnerCurrencyOutput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, spinnerCurrencyOutput.getSelectedItem().toString()+" Selected", Toast.LENGTH_SHORT).show();
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        eTextNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(eTextNumber.getText().length()>MAX_DIGIT_VALUE){
                    eTextNumber.setText(eTextNumber.getText().subSequence(0, MAX_DIGIT_VALUE));
                    eTextNumber.setSelection(eTextNumber.getText().length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
               update();
            }
        });

        historyArray = new String[15];
        for(int i = 0; i<historyArray.length; i++){
            historyArray[i] = "";
        }
    }

    private void initViews() {
        textHello = findViewById(R.id.textHello);
        textInfo = findViewById(R.id.textInfo);
        textCurrencyInput = findViewById(R.id.textCurrencyInput);
        textCurrencyOutput = findViewById(R.id.textCurrencyOutput);
        textOutput = findViewById(R.id.textOutput);
        imageView = findViewById(R.id.imageView);
        spinnerCurrencyInput = findViewById(R.id.spinnerCurrencyInput);
        spinnerCurrencyOutput = findViewById(R.id.spinnerCurrencyOutput);
        eTextNumber = findViewById(R.id.eTextNumber);
        textHistory = findViewById(R.id.textHistory);
    }

    private void update(){
        if(eTextNumber.getText().length()>0){
            double outputValue = Converter.convert(spinnerCurrencyInput.getSelectedItemPosition(), spinnerCurrencyOutput.getSelectedItemPosition(), Double.valueOf(eTextNumber.getText().toString()));
            textOutput.setText(String.format("%.3f", outputValue));
            addItemToHistory(eTextNumber.getText().toString(), spinnerCurrencyInput.getSelectedItem().toString(), textOutput.getText().toString(), spinnerCurrencyOutput.getSelectedItem().toString());
            updateTextHistory();
        }else{
            textOutput.setText("");
        }
    }

    private void addItemToHistory(String inputValue, String currencyInput, String outputValue, String currencyOutput) {
        String newItem = inputValue+" "+currencyInput+" -> "+outputValue+" "+currencyOutput;
        if(!newItem.equals(historyArray[0])){
            for(int i = historyArray.length-1; i>0; i--){
                historyArray[i] = historyArray[i-1];
            }
            historyArray[0] = newItem;
        }
    }

    private void updateTextHistory(){
        String newHistory = "";
        for(int i = 0; i<historyArray.length; i++){
            if(historyArray[i].length()<=0){
                continue;
            }
            newHistory = newHistory+historyArray[i]+"\n";
        }
        textHistory.setText(newHistory);
    }


}