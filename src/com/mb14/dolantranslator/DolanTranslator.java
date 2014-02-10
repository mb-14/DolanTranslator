package com.mb14.dolantranslator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.EditText;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DolanTranslator extends AsyncTask<String, String, String>{

	    HashMap<String, String> dictionary;
	    EditText dolanText;
	    String resultText;
		public DolanTranslator(Activity activity) 
		{
			dolanText = (EditText) activity.findViewById(R.id.dolan_text);
			InputStream is =activity.getResources().openRawResource(R.raw.dictionary);
			Writer writer = new StringWriter();
			char[] buffer = new char[1024];
			try {
			    Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			    int n;
			    while ((n = reader.read(buffer)) != -1) {
			        writer.write(buffer, 0, n);
			    }
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			    try {
					is.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
			
			String jsonString = writer.toString();

				JsonElement json = new JsonParser().parse(jsonString);
				JsonObject obj = json.getAsJsonObject();
		dictionary = new HashMap<String, String>();				
		for(Entry<String, JsonElement> entry : obj.entrySet()) {
         dictionary.put(entry.getKey(), entry.getValue().getAsString());
			
			
		}
			
					
		}

			
		public String translate(String s)
		{   
			
			String finalString = s;
			
			for(Entry<String, String> entry : dictionary.entrySet()) {
				String replace = entry.getValue();
				Pattern pat = Pattern.compile("\\b" + entry.getKey() + "\\b", Pattern.CASE_INSENSITIVE);
				Matcher mat = pat.matcher(finalString);
				finalString = mat.replaceAll(replace);
			}
						
					
			return finalString;
		}


		@Override
		protected String doInBackground(String... str) {
			resultText = translate(str[0]);
			return null;
		}
		  @Override
	        protected void onPostExecute(String result) {
			   super.onPostExecute(result);
	            dolanText.setText(resultText);
	        }
	 
}

