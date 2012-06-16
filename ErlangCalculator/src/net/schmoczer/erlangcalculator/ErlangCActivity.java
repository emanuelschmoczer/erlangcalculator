package net.schmoczer.erlangcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ErlangCActivity extends Activity {
    private EditText editTextErlang;
    private EditText editTextAgents;
    private EditText editTextProbability;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.erlangc);

        editTextErlang = (EditText) findViewById(R.id.editTextCErlang);
        editTextAgents = (EditText) findViewById(R.id.editTextCCallAgents);
        editTextProbability = (EditText) findViewById(R.id.editTextCProbability);

        Button buttonCalculate = (Button) findViewById(R.id.buttonCCalculate);

        buttonCalculate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editTextErlang.getText().toString().trim().length() > 0
                        && editTextAgents.getText().toString().trim().length() > 0) {
                    double erlang = Double.valueOf(editTextErlang.getText().toString().trim());
                    double agents = Double.valueOf(editTextAgents.getText().toString().trim());
                    if (erlang > 0 && agents > 0) {
                        double pw = calculateWaitingProbability(erlang, agents);
                        editTextProbability.setText(String.valueOf(pw));
                    }
                }

            }
        });
    }

    private double calculateWaitingProbability(double erlang, double agents) {
        double invertedPw = erlang / agents + (1 - erlang / agents) / calculateBlockingProbability(erlang, agents);
        return 1 / invertedPw;
    }

    private double calculateBlockingProbability(double erlang, double circuits) {
        double invertedPb = 1;
        for (int i = 0; i <= circuits; i++) {
            invertedPb = 1.0 + i / erlang * invertedPb;
        }
        return 1 / invertedPb;
    }
}
