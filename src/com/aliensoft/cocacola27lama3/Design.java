package com.aliensoft.cocacola27lama3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Design extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.design);
		ImageView coca1 = (ImageView) findViewById(R.id.imageButton1);
		ImageView coca2 = (ImageView) findViewById(R.id.imageButton2);
		ImageView coca3 = (ImageView) findViewById(R.id.imageButton3);

		coca1.setOnClickListener(this);
		coca2.setOnClickListener(this);
		coca3.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		Intent intent = new Intent();
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.imageButton1:

			intent.putExtra("design", "1");
			setResult(2, intent);

			finish();
			break;
		case R.id.imageButton2:

			intent.putExtra("design", "2");
			setResult(2, intent);

			finish();
			break;
		case R.id.imageButton3:

			intent.putExtra("design", "3");
			setResult(2, intent);

			finish();
			break;
		default:
			break;
		}

	}
}
