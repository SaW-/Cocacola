package com.aliensoft.cocacola27lama3;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class About extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		ImageView shadyface = (ImageView) findViewById(R.id.shdyFace);
		ImageView shadyemail = (ImageView) findViewById(R.id.shadyEmail);
		ImageView shadygoogle = (ImageView) findViewById(R.id.shadyGoogle);
		ImageView shadytwit = (ImageView) findViewById(R.id.shdyTwit);
	
		shadyemail.setOnClickListener(this);
		shadyface.setOnClickListener(this);
		shadygoogle.setOnClickListener(this);
		shadytwit.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		Intent intent = null;
		switch (arg0.getId()) {
		case R.id.shadyEmail:
			Intent emailIntents = new Intent(android.content.Intent.ACTION_SEND);
			emailIntents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			emailIntents.setType("vnd.android.cursor.item/email");
			emailIntents.putExtra(android.content.Intent.EXTRA_EMAIL,
					new String[] { "e.saw.90@gmail.com" });
			emailIntents.putExtra(android.content.Intent.EXTRA_SUBJECT,
					"كوكاكولا احلى مع...");
			emailIntents.putExtra(android.content.Intent.EXTRA_TEXT, "");
			startActivity(Intent.createChooser(emailIntents,
					"Send mail using..."));
			break;
		case R.id.shdyFace:
			try {

				this.getPackageManager().getPackageInfo("com.facebook.katana",
						0);
				Intent intent1 = new Intent(Intent.ACTION_VIEW,
						Uri.parse("fb://profile/657772079"));
				intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent1);
			} catch (Exception e) {
				startActivity(new Intent(Intent.ACTION_VIEW,
						Uri.parse("https://www.facebook.com/shady.william")));
			}
			break;
		case R.id.shdyTwit:
			try {
				this.getPackageManager().getPackageInfo("com.twitter.android",
						0);
				intent = new Intent(Intent.ACTION_VIEW,
						Uri.parse("twitter://user?user_id=566226427"));
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			} catch (Exception e) {
				intent = new Intent(Intent.ACTION_VIEW,
						Uri.parse("https://twitter.com/Shady_Amir"));
			}
			this.startActivity(intent);
			break;
		case R.id.shadyGoogle:
			try {
				Intent intent3 = new Intent(Intent.ACTION_VIEW);
				intent3.setClassName("com.google.android.apps.plus",
						"com.google.android.apps.plus.phone.UrlGatewayActivity");
				intent3.putExtra("customAppUri", "109053660584416977267");
				intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent3);
			} catch (ActivityNotFoundException e1) {
				startActivity(new Intent(
						Intent.ACTION_VIEW,
						Uri.parse("https://plus.google.com/109053660584416977267/posts")));
			}
			break;
		default:
			break;
		}

	}

}
