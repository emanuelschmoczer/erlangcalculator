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

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;

/**
 * 
 * @author Emanuel Schmoczer
 * 
 */
public class ErlangCalculatorActivity extends TabActivity {
    private static final int ABOUT = 1;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec tabSpec;
        Intent intent;

        // Erl-tab
        intent = new Intent().setClass(this, ErlangActivity.class);
        tabSpec = tabHost
                .newTabSpec("erl")
                .setIndicator(getResources().getString(R.string.erlang_text),
                        getResources().getDrawable(R.drawable.ic_tab_erl)).setContent(intent);
        tabHost.addTab(tabSpec);

        // ErlangB-tab
        intent = new Intent().setClass(this, ErlangBActivity.class);
        tabSpec = tabHost
                .newTabSpec("b")
                .setIndicator(getResources().getString(R.string.erlangb_text),
                        getResources().getDrawable(R.drawable.ic_tab_b)).setContent(intent);
        tabHost.addTab(tabSpec);

        // ErlangC-tab
        intent = new Intent().setClass(this, ErlangCActivity.class);
        tabSpec = tabHost
                .newTabSpec("c")
                .setIndicator(getResources().getString(R.string.erlangc_text),
                        getResources().getDrawable(R.drawable.ic_tab_c)).setContent(intent);
        tabHost.addTab(tabSpec);

        tabHost.setCurrentTab(0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about_menuitem) {
            this.showDialog(ABOUT);
        }
        return true;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.dialog_about_text)).setPositiveButton(
                R.string.ok, new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
        dialog = builder.create();
        return dialog;
    }
}