package com.example.customsyncadapter.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDatabaseHelper extends SQLiteOpenHelper{

	final static int VERSION = 1;
	final static String DATABASENAME="simplesync";

	
	public AppDatabaseHelper(Context context) {
		super(context, DATABASENAME, null, VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db){
		
		db.execSQL("create table personas ( _ID integer primary key autoincrement, nombre varchar(200) not null, apellido varchar(200) not null)");		
	
	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//nothing here move along
	}

}
