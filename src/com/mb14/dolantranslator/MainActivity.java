package com.mb14.dolantranslator;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	DolanTranslator translator;
	EditText englishText, dolanText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String fontPath = "fonts/child.ttf";
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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
