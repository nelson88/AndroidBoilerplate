package com.example.customsyncadapter;

import pepito.com.R;
import android.app.Activity;
//import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.customsyncadapter.db.AppContract;
import com.example.customsyncadapter.db.AppProvider;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Uri uri = AppContract.CONTENT_URI.buildUpon().build();
		Button btninsert;
		btninsert = (Button) findViewById(R.id.btninsert);
		
		btninsert.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {	
				Intent intent = new Intent(MainActivity.this, ActivityInsert.class);
				startActivity(intent);
			}
			
		});
		/*int rowsDeleted = 0;
		
		
		ContentValues values = new ContentValues();
		values.put("nombre", "Luis");
		values.put("apellido", "Castillo");
		Uri uriInserted = getContentResolver().insert(uri, values);
		*/
		
		//esto va a borrar el que acabas de insertar
		/*rowsDeleted = getContentResolver().delete(uriInserted, null, null);
		Log.i("App", "Valor : " + rowsDeleted + " uri "+ uriInserted);*/
		
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
