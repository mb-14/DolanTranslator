package com.mb14.dolantranslator;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class MainActivity extends SherlockActivity {
	DolanTranslator translator;
	EditText englishText, dolanText;
	ActionBar actionbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String fontPath = "fonts/child.ttf";
		actionbar = getSupportActionBar();
		 Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
		 TextView dolanTitle = (TextView) findViewById(R.id.dolan_title);
		 TextView englishTitle = (TextView) findViewById(R.id.english_title);
		 dolanTitle.setTypeface(tf);
		 englishTitle.setTypeface(tf);
			translator = new DolanTranslator(this);
		englishText = (EditText)findViewById(R.id.english_text);
		dolanText = (EditText)findViewById(R.id.dolan_text);
		englishText.setTypeface(tf);
		dolanText.setTypeface(tf);
		 englishText.addTextChangedListener(new TextWatcher()
		    {
		        @Override
		        public void afterTextChanged(Editable mEdit) 
		        {
		            String translated = translator.translate(mEdit.toString());
		            dolanText.setText(translated);
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
