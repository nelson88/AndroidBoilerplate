package com.example.customsyncadapter;

import com.example.customsyncadapter.db.AppContract;

import pepito.com.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityInsert extends Activity
{
	
	EditText txtnombre;
	EditText txtapellido;
	Button btnguardar;
	private InsertAsyncTask insert;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert);
		
		txtnombre = (EditText) findViewById(R.id.txtnombre);
		txtapellido = (EditText) findViewById(R.id.txtapellido);
		btnguardar = (Button) findViewById(R.id.btnguardar);
		
		btnguardar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				insert = new InsertAsyncTask();
				insert.execute();
			}
		});
	}
	
	private class InsertAsyncTask extends AsyncTask<Void, Void, Void>{

		Uri uri = AppContract.CONTENT_URI.buildUpon().build();
		
		protected void onPreExecute(){
			//btnguardar.setEnabled(false);
			final ProgressDialog progress = ProgressDialog.show(ActivityInsert.this, "", "Loading please wait...");
			progress.setCancelable(true);
		}
		
		protected Void doInBackground(Void... params){
			
			try
			{
				ContentValues values = new ContentValues();
				values.put("nombre", txtnombre.getText().toString());
				values.put("apellido", txtapellido.getText().toString());
				Uri uriInsert = getContentResolver().insert(uri, values);
				Log.i("App", "insert: " + uriInsert);
				
				Cursor cursor = getContentResolver().query(uri, null, null, null, null);
				Log.i("App","Count "+cursor.getCount());
				while(cursor.moveToNext()){
					Log.i("App","record "+ cursor.getInt(0) +" - " + cursor.getString(1) + " "+ cursor.getString(2));
					
				}
				
			} catch(Exception e) {
				Toast.makeText(getApplication(), "TERMINO ASYNCTASK" + e, Toast.LENGTH_LONG).show();
			}
			
			return null;
		}
		
		protected void onPostExecute(Void result){
			txtnombre.setText("");
			txtapellido.setText("");
			
			//Toast.makeText(getApplication(), "TERMINO ASYNCTASK", Toast.LENGTH_LONG).show();
        }  
		
	}

}
