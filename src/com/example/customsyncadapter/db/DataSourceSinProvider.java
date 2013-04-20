package com.example.customsyncadapter.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**Es para que veas como se hace sin content provider*/
public class DataSourceSinProvider {
	
	private SQLiteDatabase database;
	private SimpleDatabaseHelper databaseHelper;
	
	public DataSourceSinProvider(Context context){
		databaseHelper = new SimpleDatabaseHelper(context);
	}
	
	public void open() throws SQLException {
		database = databaseHelper.getWritableDatabase();
	}
	
	public void close(){
		databaseHelper.close();
	}

	public Cursor fetchPersonas(){
		return database.query("personas", null, null, null, null, null, null);
	}
	
}
