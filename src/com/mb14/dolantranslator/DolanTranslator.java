package com.mb14.dolantranslator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class DolanTranslator {

		
	    private JSONObject json;
		
		public DolanTranslator(Context context) 
		{
			InputStream is = context.getResources().openRawResource(R.raw.dictionary);
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
			try {
				json = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}
					
		}

			
		public String translate(String s)
		{   
			
			Random rand = new Random();
			String finalString = s;
			for(Iterator<String> iter = json.keys();iter.hasNext();) {
			    String key = iter.next();
			    try {
		            JSONArray dolan = json.getJSONArray(key);
		            String replace = dolan.getString(rand.nextInt(dolan.length()));
		            Pattern pat = Pattern.compile("\\b" + key + "\\b", Pattern.CASE_INSENSITIVE);
					Matcher mat = pat.matcher(finalString);
					finalString = mat.replaceAll(replace);
		        } catch (JSONException e) {
		        	e.printStackTrace();
		        }
		
			}
						
					
			return finalString;
		}
		
	 
}

