package com.aliensoft.cocacola27lama3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btnLoadImage1;
	TextView textSource1;
	EditText editTextCaptionEN, editTextCaptionAR;
	Button btnProcessing, btnSaving;
	ImageView imageResult;
	String saveText;
	final int RQS_IMAGE1 = 1;
	Bitmap newBitmap = null;
	Uri source1;
	Bitmap processedBitmap;
	int design = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dialoge();
		editTextCaptionEN = (EditText) findViewById(R.id.captionEN);
		editTextCaptionAR = (EditText) findViewById(R.id.captionAR);
		btnProcessing = (Button) findViewById(R.id.processing);
		btnSaving = (Button) findViewById(R.id.share);
		imageResult = (ImageView) findViewById(R.id.result);
		btnSaving.setEnabled(false);
		btnSaving.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				save();
			}

		});
		btnProcessing.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				processedBitmap = ProcessingBitmap();
				if (processedBitmap != null) {
					imageResult.setImageBitmap(processedBitmap);
					Toast.makeText(getApplicationContext(), "تم",
							Toast.LENGTH_LONG).show();
				}

			}
		});

		AdView adView = (AdView) findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
	}

	void save() {
		// TODO Auto-generated method stub
		File file = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
						+ "/" + saveText + ".png");

		try {
			newBitmap.compress(Bitmap.CompressFormat.PNG, 100,
					new FileOutputStream(file));

			Intent mediaScanIntent = new Intent(
					Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
			File f = file;
			Uri contentUri = Uri.fromFile(f);
			mediaScanIntent.setData(contentUri);
			sendBroadcast(mediaScanIntent);

			Toast.makeText(getApplicationContext(), "تم حفظ", Toast.LENGTH_LONG)
					.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void dialoge() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("حقوق الملكية");
		builder.setMessage("هذا البرنامج ليس له علاقة بشركة كوكاكولا وجميع حقوق الدعاية والاعلان تعود لشركة كوكاكولا و لكن الهدف منه ادخال الفرحة الى قلوب الناس .. رمضان كريم");

		builder.setPositiveButton("موافق",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}

				});

		builder.setNegativeButton("خروج",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// Do nothing
						finish();
						dialog.dismiss();
					}
				});

		AlertDialog alert = builder.create();
		alert.show();

	}

	private Bitmap ProcessingBitmap() {
		Bitmap bm1 = null;
		newBitmap = null;
		String captionStringEN = editTextCaptionEN.getText().toString();
		String captionStringAR = editTextCaptionAR.getText().toString();
		if (!captionStringEN.equals("") && captionStringEN != null
				&& !captionStringAR.equals("") && captionStringAR != null) {
			saveText = captionStringEN + design;

			switch (design) {
			case 1:
				newBitmap = draw1(bm1, newBitmap, captionStringEN,
						captionStringAR);
				break;
			case 2:
				newBitmap = draw2(bm1, newBitmap, captionStringEN,
						captionStringAR);
				break;
			case 3:
				newBitmap = draw3(bm1, newBitmap, captionStringEN,
						captionStringAR);
				break;

			default:
				newBitmap = draw1(bm1, newBitmap, captionStringEN,
						captionStringAR);
				break;
			}

		} else {
			Toast.makeText(getApplicationContext(), "أدخل أسمك !",
					Toast.LENGTH_LONG).show();
		}

		return newBitmap;
	}

	private Bitmap draw1(Bitmap bm1, Bitmap newBitmap, String captionStringEN,
			String captionStringAR) {
		InputStream is = this.getResources().openRawResource(R.drawable.coca);
		bm1 = BitmapFactory.decodeStream(is);

		Config config = bm1.getConfig();
		if (config == null) {
			config = Bitmap.Config.ARGB_8888;
		}

		newBitmap = Bitmap
				.createBitmap(bm1.getWidth(), bm1.getHeight(), config);
		Canvas newCanvas = new Canvas(newBitmap);

		newCanvas.drawBitmap(bm1, 0, 0, null);

		Paint paintText1 = new Paint(Paint.ANTI_ALIAS_FLAG);
		paintText1.setColor(Color.RED);
		paintText1.setTextSize(65);
		paintText1
				.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
		paintText1.setStyle(Style.FILL);

		Rect rectText1 = new Rect();
		paintText1.getTextBounds(captionStringAR, 0, captionStringAR.length(),
				rectText1);

		newCanvas.drawText(captionStringAR, (newCanvas.getWidth() * 0.32f)
				- (rectText1.width() / 2), newCanvas.getHeight() / 2,
				paintText1);

		Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
		paintText.setColor(Color.WHITE);
		paintText.setTextSize(60);
		paintText.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
		paintText.setStyle(Style.FILL);

		Rect rectText = new Rect();
		paintText.getTextBounds(captionStringEN, 0, captionStringEN.length(),
				rectText);

		newCanvas.save();
		newCanvas.rotate(270, newCanvas.getWidth() / 1.35f,
				(newCanvas.getHeight() * 0.51f) + (rectText.width() / 2));

		newCanvas.drawText(captionStringEN, newCanvas.getWidth() / 1.35f,
				(newCanvas.getHeight() * 0.51f) + (rectText.width() / 2),
				paintText);
		newCanvas.restore();

		btnSaving.setEnabled(true);
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(getWindow().getCurrentFocus()
				.getWindowToken(), 0);

		return newBitmap;
	}

	private Bitmap draw3(Bitmap bm1, Bitmap newBitmap, String captionStringEN,
			String captionStringAR) {
		InputStream is = this.getResources().openRawResource(R.drawable.b);
		bm1 = BitmapFactory.decodeStream(is);

		Config config = bm1.getConfig();
		if (config == null) {
			config = Bitmap.Config.ARGB_8888;
		}

		newBitmap = Bitmap
				.createBitmap(bm1.getWidth(), bm1.getHeight(), config);
		Canvas newCanvas = new Canvas(newBitmap);

		newCanvas.drawBitmap(bm1, 0, 0, null);

		Paint paintText1 = new Paint(Paint.ANTI_ALIAS_FLAG);
		paintText1.setColor(Color.WHITE);
		paintText1.setTextSize(40);
		paintText1
				.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
		paintText1.setStyle(Style.FILL);

		Rect rectText1 = new Rect();
		paintText1.getTextBounds(captionStringAR, 0, captionStringAR.length(),
				rectText1);

		newCanvas.drawText(captionStringAR, (newCanvas.getWidth() * 0.20f)
				- (rectText1.width() / 2), newCanvas.getHeight() / 1.8f,
				paintText1);

		Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
		paintText.setColor(Color.WHITE);
		paintText.setTextSize(40);
		paintText.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
		paintText.setStyle(Style.FILL);

		Rect rectText = new Rect();
		paintText.getTextBounds(captionStringAR, 0, captionStringAR.length(),
				rectText);

		newCanvas.drawText(captionStringAR, (newCanvas.getWidth() * 0.5f)
				- (rectText.width() / 2), newCanvas.getHeight() / 1.08f,
				paintText);

		btnSaving.setEnabled(true);
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(getWindow().getCurrentFocus()
				.getWindowToken(), 0);

		return newBitmap;
	}

	private Bitmap draw2(Bitmap bm1, Bitmap newBitmap, String captionStringEN,
			String captionStringAR) {
		InputStream is = this.getResources().openRawResource(R.drawable.a);
		bm1 = BitmapFactory.decodeStream(is);

		Config config = bm1.getConfig();
		if (config == null) {
			config = Bitmap.Config.ARGB_8888;
		}

		newBitmap = Bitmap
				.createBitmap(bm1.getWidth(), bm1.getHeight(), config);
		Canvas newCanvas = new Canvas(newBitmap);

		newCanvas.drawBitmap(bm1, 0, 0, null);

		Paint paintText1 = new Paint(Paint.ANTI_ALIAS_FLAG);
		paintText1.setColor(Color.RED);
		paintText1.setTextSize(70);
		paintText1
				.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
		paintText1.setStyle(Style.FILL);

		Rect rectText1 = new Rect();
		paintText1.getTextBounds(captionStringAR, 0, captionStringAR.length(),
				rectText1);

		newCanvas.drawText(captionStringAR, (newCanvas.getWidth() * 0.27f)
				- (rectText1.width() / 2), newCanvas.getHeight() / 1.7f,
				paintText1);

		Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
		paintText.setColor(Color.WHITE);
		paintText.setTextSize(50);
		paintText.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
		paintText.setStyle(Style.FILL);

		Rect rectText = new Rect();
		paintText.getTextBounds(captionStringAR, 0, captionStringAR.length(),
				rectText);

		newCanvas.drawText(captionStringAR, (newCanvas.getWidth() * 0.75f)
				- (rectText.width() / 2), newCanvas.getHeight() / 2.4f,
				paintText);

		btnSaving.setEnabled(true);
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(getWindow().getCurrentFocus()
				.getWindowToken(), 0);

		return newBitmap;
	}

	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_design:

			Intent i = new Intent(this, Design.class);
			startActivityForResult(i, 2);
			break;

		case R.id.action_exit:
			finish();
			break;
		case R.id.action_share:
			processedBitmap = ProcessingBitmap();
			imageResult.setImageBitmap(processedBitmap);
			String captionStringEN = editTextCaptionEN.getText().toString();
			String captionStringAR = editTextCaptionAR.getText().toString();
			if (!captionStringEN.equals("") && captionStringEN != null
					&& !captionStringAR.equals("") && captionStringAR != null) {
				save();

				Uri uri = Uri
						.parse("file:///"
								+ Environment
										.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
								+ "/" + saveText + ".png");

				Intent shareIntent = new Intent(Intent.ACTION_SEND);
				shareIntent
						.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
				shareIntent.setType("image/*");

				// For a file in shared storage. For data in private
				// storage, use a ContentProvider.

				shareIntent.putExtra(Intent.EXTRA_TEXT,
						"http://www.goo.gl/TEJbJ7");
				shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
				startActivity(Intent.createChooser(shareIntent, "مشاركة"));
			}
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		// check if the request code is same as what is passed here it is 2
		if (data != null) {
			String message = data.getStringExtra("design");
			design = Integer.parseInt(message);
		}

	}

}
