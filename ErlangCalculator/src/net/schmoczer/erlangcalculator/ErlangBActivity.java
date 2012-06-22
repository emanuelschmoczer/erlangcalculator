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
