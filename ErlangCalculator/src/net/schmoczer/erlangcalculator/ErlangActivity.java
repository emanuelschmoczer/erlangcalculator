package net.schmoczer.erlangcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ErlangActivity extends Activity {
    private EditText editTextCalls;
    private EditText editTextAvgCallTime;
    private EditText editTextObservationPeriod;
    private EditText editTextErlang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.erlang);

        editTextCalls = (EditText) findViewById(R.id.editTextCalls);
        editTextAvgCallTime = (EditText) findViewById(R.id.editTextAvgCallTime);
        editTextObservationPeriod = (EditText) findViewById(R.id.editTextObservationPeriod);
        editTextErlang = (EditText) findViewById(R.id.editTextErlErlang);
        editTextErlang.setEnabled(false);
        Button buttonCalculate = (Button) findViewById(R.id.buttonErlCalculate);

        buttonCalculate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editTextCalls.getEditableText().toString().trim().length() > 0
                        && editTextAvgCallTime.getEditableText().toString().trim().length() > 0
                        && editTextObservationPeriod.getEditableText().toString().trim().length() > 0) {
                    double calls = Double.valueOf(editTextCalls.getText().toString().trim());
                    double avgCallTime = Double.valueOf(editTextAvgCallTime.getText().toString()
                            .trim());
                    double observationPeriod = Double.valueOf(editTextObservationPeriod.getText()
                            .toString().trim());
                    if (calls > 0 && avgCallTime > 0 && observationPeriod > 0) {
                        double erlang = calls * avgCallTime / observationPeriod;
                        editTextErlang.setText(String.valueOf(erlang));
                    } else {
                        Toast.makeText(ErlangActivity.this, "TODO", Toast.LENGTH_LONG).show();// TODO
                    }
                } else {
                    Toast.makeText(ErlangActivity.this, "TODO", Toast.LENGTH_LONG).show();// TODO
                }

            }
        });
        // TODO: reset button
    }

    @Override
    protected void onPause() {
        // TODO
    }
}
