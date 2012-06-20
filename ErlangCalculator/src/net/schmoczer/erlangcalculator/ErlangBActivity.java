package net.schmoczer.erlangcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ErlangBActivity extends Activity {
    private EditText editTextErlang;
    private EditText editTextCircuits;
    private EditText editTextProbability;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.erlangb);

        editTextErlang = (EditText) findViewById(R.id.editTextBErlang);
        editTextCircuits = (EditText) findViewById(R.id.editTextBCurcuits);
        editTextProbability = (EditText) findViewById(R.id.editTextBProbability);
        editTextProbability.setEnabled(false);
        Button buttonCalculate = (Button) findViewById(R.id.buttonBCalculate);

        buttonCalculate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editTextErlang.getEditableText().toString().trim().length() > 0
                        && editTextCircuits.getEditableText().toString().trim().length() > 0) {
                    double erlang = Double.valueOf(editTextErlang.getText().toString().trim());
                    double circuits = Double.valueOf(editTextCircuits.getText().toString());
                    if (erlang > 0 && circuits > 0) {
                        double pb = calculateBlockingProbability(erlang, circuits);
                        editTextProbability.setText(String.valueOf(pb));
                    } else {
                        Toast.makeText(ErlangBActivity.this,
                                getResources().getString(R.string.error_nozero), Toast.LENGTH_LONG)
                                .show();
                    }
                } else {
                    Toast.makeText(ErlangBActivity.this,
                            getResources().getString(R.string.error_nonull), Toast.LENGTH_LONG)
                            .show();
                }

            }
        });
    }

    private double calculateBlockingProbability(double erlang, double circuits) {
        double invertedPb = 1;
        for (int i = 0; i <= circuits; i++) {
            invertedPb = 1.0 + i / erlang * invertedPb;
        }
        return 1 / invertedPb;
    }
}
