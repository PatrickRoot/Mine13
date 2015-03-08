package org.eu.sixlab.android.sixcalculatorlite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button plusButton = (Button) findViewById(R.id.button_plus);
		Button minusButton = (Button) findViewById(R.id.button_minus);
		Button multiplicationButton = (Button) findViewById(R.id.button_multiplication);
		Button divisionButton = (Button) findViewById(R.id.button_division);
		Button clearButton = (Button) findViewById(R.id.button_clear);
		Button clearEButton = (Button) findViewById(R.id.button_clear_e);
		
		clearEButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView resultTextView = (TextView) findViewById(R.id.textView2);
				resultTextView.setText("0");
				EditText numText = (EditText) findViewById(R.id.editText1);
				numText.setText("");
				numText.requestFocus();
			}
		});
		
		clearButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText numText = (EditText) findViewById(R.id.editText1);
				numText.setText("");
				numText.requestFocus();
			}
		});
		
		plusButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText numText = (EditText) findViewById(R.id.editText1);
				String input = numText.getText().toString().trim();
				if(isNum(input)){
					TextView resultTextView = (TextView) findViewById(R.id.textView2);
					String result = resultTextView.getText().toString().trim();
					if(isNum(result)){
						Long pre = Long.valueOf(result);
						Long aft = Long.valueOf(input);
						Long number = pre + aft;
						resultTextView.setText(String.valueOf(number));
					}
				}
			}
		});
		
		minusButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText numText = (EditText) findViewById(R.id.editText1);
				String input = numText.getText().toString().trim();
				if(isNum(input)){
					TextView resultTextView = (TextView) findViewById(R.id.textView2);
					String result = resultTextView.getText().toString().trim();
					if(isNum(result)){
						Long pre = Long.valueOf(result);
						Long aft = Long.valueOf(input);
						Long number = pre - aft;
						resultTextView.setText(String.valueOf(number));
					}
				}
			}
		});
		
		multiplicationButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText numText = (EditText) findViewById(R.id.editText1);
				String input = numText.getText().toString().trim();
				if(isNum(input)){
					TextView resultTextView = (TextView) findViewById(R.id.textView2);
					String result = resultTextView.getText().toString().trim();
					if(isNum(result)){
						Long pre = Long.valueOf(result);
						Long aft = Long.valueOf(input);
						Long number = pre * aft;
						resultTextView.setText(String.valueOf(number));
					}
				}
			}
		});
		
		divisionButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText numText = (EditText) findViewById(R.id.editText1);
				String input = numText.getText().toString().trim();
				if(isNum(input)){
					TextView resultTextView = (TextView) findViewById(R.id.textView2);
					String result = resultTextView.getText().toString().trim();
					if(isNum(result)){
						Long pre = Long.valueOf(result);
						Long aft = Long.valueOf(input);
						Long number = pre / aft;
						resultTextView.setText(String.valueOf(number));
					}
				}
			}
		});
		
		EditText numText = (EditText) findViewById(R.id.editText1);
		numText.setText("");
//		numText.setFocusable(true);
//		numText.setFocusableInTouchMode(true);
		numText.requestFocus();
		
//		InputMethodManager imm = (InputMethodManager) numText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//		imm.showSoftInput(numText, 0);
	}
	
	public boolean isNum(String str){
		Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?"); 
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false;
		}
		return true; 
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
}
