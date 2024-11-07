package com.example.simplecalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
private TextView resultTextView;
private StringBuilder input = new StringBuilder();
private String operator ="";
        private double firstValue=0;

        @SuppressLint("MissingInflatedId")
        @Override

    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState) ;
            setContentView(R.layout.activity_main);
            resultTextView = findViewById(R.id.resultTextView);


// Number buttons
            setNumberButton(R.id.button0, "0");
            setNumberButton(R.id.button1, "1");
            setNumberButton(R.id.button2, "2");
            setNumberButton(R.id.button3, "3");
            setNumberButton(R.id.button4, "4");
            setNumberButton(R.id.button5, "5");
            setNumberButton(R.id.button6, "6");
            setNumberButton(R.id.button7, "7");
            setNumberButton(R.id.button8, "8");
            setNumberButton(R.id.button9, "9");

            // Operator buttons
            setOperatorButton(R.id.buttonPlus, "+");
            setOperatorButton(R.id.buttonMinus, "-");
            setOperatorButton(R.id.buttonMultiply, "*");
            setOperatorButton(R.id.buttonDivide, "/");

            // Clear button
            Button clearButton = findViewById(R.id.buttonClear);
            clearButton.setOnClickListener(v -> {
                input.setLength(0);
                operator = "";
                firstValue = 0;
                resultTextView.setText("0");
            });

            // Equal button
            Button equalButton = findViewById(R.id.buttonEqual);
            equalButton.setOnClickListener(v -> {
                if (operator.isEmpty() || input.length() == 0) return;

                double secondValue = Double.parseDouble(input.toString());
                double result = calculate(firstValue, secondValue, operator);
                resultTextView.setText(String.valueOf(result));
                input.setLength(0); // Clear input for the next calculation
            });
        }

    private void setNumberButton(int buttonId, String number) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            input.append(number);
            resultTextView.setText(input.toString());
        });
    }

    private void setOperatorButton(int buttonId, String op) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            if (input.length() == 0) return;

            firstValue = Double.parseDouble(input.toString());
            operator = op;
            input.setLength(0);
        });
    }

    private double calculate(double a, double b, String op) {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return b != 0 ? a / b : 0; // Avoid division by zero
            default:
                return 0;
        }
    }
}