package net.schmoczer.erlangcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ErlangCalculatorActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button erlangActivityButton = (Button) findViewById(R.id.buttonErlangActivity);
        erlangActivityButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ErlangCalculatorActivity.this, ErlangActivity.class);
				startActivity(intent);
			}
		});
    }
}