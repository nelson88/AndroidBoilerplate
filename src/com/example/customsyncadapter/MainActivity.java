package com.example.customsyncadapter;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.example.customsyncadapter.db.DataSourceSinProvider;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DataSourceSinProvider dataSource = new DataSourceSinProvider (this);
		dataSource.open();
		Cursor cursor = dataSource.fetchPersonas();
		
		while(cursor.moveToNext()){
			Log.i("Sample", cursor.getString(1));
			Log.i("Sample", cursor.getString(2));
		}
		cursor.close();
		dataSource.close();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
