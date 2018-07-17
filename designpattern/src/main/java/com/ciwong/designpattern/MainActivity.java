package com.ciwong.designpattern;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	Handler mHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mHandler = new Handler(Looper.getMainLooper());
		TextView textView = (TextView) findViewById(R.id.tv);
		textView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						textView.setText("change");
					}
				});
			}
		});
	}
}
