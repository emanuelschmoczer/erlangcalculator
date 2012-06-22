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
        editTextProbability.setEnabled(false);

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
                    } else {
                        Toast.makeText(ErlangCActivity.this,
                                getResources().getString(R.string.error_nozero), Toast.LENGTH_LONG)
                                .show();
                    }
                } else {
                    Toast.makeText(ErlangCActivity.this,
                            getResources().getString(R.string.error_nonull), Toast.LENGTH_LONG)
                            .show();
                }

            }
        });
    }

    private double calculateWaitingProbability(double erlang, double agents) {
        double invertedPw = erlang / agents + (1 - erlang / agents)
                / calculateBlockingProbability(erlang, agents);
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
