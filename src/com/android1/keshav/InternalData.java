package com.android1.keshav;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InternalData extends Activity implements OnClickListener{

	EditText sharedData;
	TextView dataResults;
	Button save,load;
	FileOutputStream fos;
	
	String FILENAME="InternalString",data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		setupVariables();
	}

	 
	private void setupVariables() {
		// TODO Auto-generated method stub
		
		save=(Button)findViewById(R.id.bSave);
		load=(Button)findViewById(R.id.bLoad);
		sharedData=(EditText)findViewById(R.id.etSharedPrefs);
		dataResults=(TextView)findViewById(R.id.tvLoadSharedPrefs);
		save.setOnClickListener(this);
		load.setOnClickListener(this);
		try
		{
			fos=openFileOutput(FILENAME, Context.MODE_PRIVATE);
			fos.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		case R.id.bSave:
			data=sharedData.getText().toString();
			
			//Saving data via file
			/*File f=new File(FILENAME);
			try{
			fos=new FileOutputStream(f);
			//write some data
			fos.close();
			}
			catch(FileNotFoundException e){
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			try {
				fos=openFileOutput(FILENAME, Context.MODE_PRIVATE);
				fos.write(data.getBytes());
				fos.close();
			}  catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.bLoad:
			new LoadSomeStuff().execute(FILENAME);
			break;
		}
	
	}
	public class LoadSomeStuff extends AsyncTask<String, Integer, String>{

		ProgressDialog dialog;
		
		public void onPreExecute(){
			
			//example of setting up something
			dialog=new ProgressDialog(InternalData.this);
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setMax(100);
			dialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			FileInputStream fis=null;
			String collected=null;
			
			for(int i=0; i<20 ; i++){
				publishProgress(5);
				try {
					Thread.sleep(88);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			dialog.dismiss();
			try {
				fis=openFileInput(FILENAME);
				byte[] dataArray = new byte[fis.available()];
				while(fis.read(dataArray)!=-1){
					collected=new String(dataArray);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					fis.close();
					return collected;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
		
		protected void onProgressUpdated(Integer... progress){
			
			dialog.incrementProgressBy(progress[0]);
		}
		
		protected void onPostExecute(String result) {
			
			dataResults.setText(result);
		}
		
		
	}
}
