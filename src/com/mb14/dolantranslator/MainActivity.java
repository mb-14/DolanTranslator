package com.mb14.dolantranslator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

@SuppressLint("NewApi")
public class MainActivity extends SherlockActivity {
	DolanTranslator translator;
	EditText englishText, dolanText;
	ActionBar actionbar;
     ImageButton copyButton;
	 Activity activity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String fontPath = "fonts/child.ttf";
		actionbar = getSupportActionBar();
		activity = this;
		 Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
		 TextView dolanTitle = (TextView) findViewById(R.id.dolan_title);
		 TextView englishTitle = (TextView) findViewById(R.id.english_title);
		 dolanTitle.setTypeface(tf);
		 englishTitle.setTypeface(tf);
		englishText = (EditText)findViewById(R.id.english_text);
		dolanText = (EditText)findViewById(R.id.dolan_text);
		englishText.setTypeface(tf);
		dolanText.setTypeface(tf);
		copyButton = (ImageButton) findViewById(R.id.copy_button);
		copyButton.setOnClickListener(new OnClickListener(){
	    	   @Override
	    	   public void onClick(View arg0) {
	    		   int sdk = android.os.Build.VERSION.SDK_INT;
	    		   if(sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
	    		       android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
	    		       clipboard.setText(dolanText.getText().toString());
	    		   } else {
	    		       android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE); 
	    		       android.content.ClipData clip = android.content.ClipData.newPlainText("Dolan Translator",dolanText.getText().toString());
	    		       clipboard.setPrimaryClip(clip);
	    		   }
	    		   Toast.makeText(getApplicationContext(), "Copied to clipboard.",
	    				   Toast.LENGTH_SHORT).show();
	    	   }}
	);
		 englishText.addTextChangedListener(new TextWatcher()
		    {
			 
		        @Override
		        public void afterTextChanged(Editable mEdit) 
		        { 
		        	if(translator!=null){
		        	translator.cancel(true);
		        	}
		        	
		           translator = new DolanTranslator(activity);
		           translator.execute(mEdit.toString());
		        	
		             }

		        public void beforeTextChanged(CharSequence s, int start, int count, int after){}

		        public void onTextChanged(CharSequence s, int start, int before, int count){}
		    });
	}

	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
		    MenuInflater inflater = getSupportMenuInflater();
		    inflater.inflate(R.menu.main, menu);
		    return true;
		}
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
		   Intent i = new Intent(MainActivity.this,AboutActivity.class);
	       startActivity(i);
		 	return true;
	    }
}
