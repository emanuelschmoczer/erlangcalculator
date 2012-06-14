package net.schmoczer.erlangcalculator;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class ErlangCalculatorActivity extends TabActivity {
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
}