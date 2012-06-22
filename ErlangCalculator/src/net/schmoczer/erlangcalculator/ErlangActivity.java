/*
    ErlangCalculator, a erlang calculator for android.
    Copyright (C) 2012  Emanuel Schmoczer <emanuel.schmoczer@gmail.com>

    This file is part of ErlangCalculator.

    ErlangCalculator is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    ErlangCalculator is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with ErlangCalculator.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.schmoczer.erlangcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 
 * @author Emanuel Schmoczer
 * 
 */
public class ErlangActivity extends Activity {
    private EditText editTextCalls;
    private EditText editTextAvgCallTime;
    private EditText editTextWrapUpTime;
    private EditText editTextObservationPeriod;
    private EditText editTextErlang;
    public double erlang = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.erlang);

        editTextCalls = (EditText) findViewById(R.id.editTextCalls);
        editTextAvgCallTime = (EditText) findViewById(R.id.editTextAvgCallTime);
        editTextWrapUpTime = (EditText) findViewById(R.id.editTextWrapUpTime);
        editTextObservationPeriod = (EditText) findViewById(R.id.editTextObservationPeriod);
        editTextErlang = (EditText) findViewById(R.id.editTextErlErlang);
        editTextErlang.setEnabled(false);
        Button buttonCalculate = (Button) findViewById(R.id.buttonErlCalculate);

        buttonCalculate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editTextCalls.getEditableText().toString().trim().length() > 0
                        && editTextAvgCallTime.getEditableText().toString().trim().length() > 0
                        && editTextWrapUpTime.getEditableText().toString().trim().length() >= 0
                        && editTextObservationPeriod.getEditableText().toString().trim().length() > 0) {
                    double calls = Double.valueOf(editTextCalls.getText().toString().trim());
                    double avgCallTime = Double.valueOf(editTextAvgCallTime.getText().toString()
                            .trim());
                    double wrapUpTime = Double.valueOf(editTextWrapUpTime.getText().toString()
                            .trim());
                    double observationPeriod = Double.valueOf(editTextObservationPeriod.getText()
                            .toString().trim());
                    if (calls > 0 && avgCallTime > 0 && wrapUpTime >= 0 && observationPeriod > 0) {
                        erlang = calls * (avgCallTime + wrapUpTime) / observationPeriod;
                        editTextErlang.setText(String.valueOf(erlang));
                    } else {
                        Toast.makeText(ErlangActivity.this,
                                getResources().getString(R.string.error_nozero), Toast.LENGTH_LONG)
                                .show();
                    }
                } else {
                    Toast.makeText(ErlangActivity.this,
                            getResources().getString(R.string.error_nonull), Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
        // TODO: reset button
    }

//    @Override
//    protected void onPause() {
//        // TODO Auto-generated method stub
//        super.onPause();
//    }
}
