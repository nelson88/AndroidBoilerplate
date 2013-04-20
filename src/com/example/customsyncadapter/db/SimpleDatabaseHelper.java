package com.example.customsyncadapter.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SimpleDatabaseHelper extends SQLiteOpenHelper{

	final static int VERSION = 1;
	final static String DATABASENAME="simplesync";

	
	public SimpleDatabaseHelper(Context context) {
		super(context, DATABASENAME, null, VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL("create table personas ( _ID int primary key, nombre varchar(200) not null, apellido varchar(200) not null)");		
		
		//estos son datos de pruebas
		db.execSQL("insert into personas (nombre, apellido) values('jose','ayerdis')");
		db.execSQL("insert into personas (nombre, apellido) values('jose1','ayerdis1')");
		db.execSQL("insert into personas (nombre, apellido) values('jose2','ayerdis2')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//nothing here move along
	}

}
