package com.example.customsyncadapter.db;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class AppProvider extends ContentProvider {

	private AppDatabaseHelper database;
	public static final int PERSONA = 0x400;
	public static final int PERSONA_ID = 0x401;
	//El uri matcher es una clase que mapea un Authority con un elemento especifico
	 private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	  static {
		  uriMatcher.addURI(AppContract.AUTHORITY, AppContract.BASE_PATH, PERSONA);
	    uriMatcher.addURI(AppContract.AUTHORITY, AppContract.BASE_PATH + "/*", PERSONA_ID);
	  }

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int uriId = uriMatcher.match(uri);
		SQLiteDatabase db = database.getWritableDatabase();
		int rowsDeleted = 0;
		switch(uriId){
		case PERSONA:
			rowsDeleted = db.delete("personas", selection, selectionArgs);
			Log.i("App", "ENTRO A PERSONAS");
			break;
		case PERSONA_ID:
			String id = uri.getLastPathSegment();
			if(TextUtils.isEmpty(selection)){// concentremonos aqui esta malo algo
				rowsDeleted = db.delete("personas", " _ID = " + id, null);
				Log.i("App", "single record "+ rowsDeleted);
			} else {
				rowsDeleted = db.delete("personas", "_ID = " + uri.getLastPathSegment() + "and" + selection, selectionArgs);
			}
			
			break;
		default:
			throw new IllegalStateException("Unknown uri "+uri);
		}
		
		getContext().getContentResolver().notifyChange(uri, null);
		
		
		return rowsDeleted;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriId = uriMatcher.match(uri);
		SQLiteDatabase db = database.getWritableDatabase();
		
		long id = 0;
		Uri uriInserted = null;
		switch(uriId){
		case PERSONA:
		case PERSONA_ID:
			id  = db.insert("personas", null, values);
			uriInserted = AppContract.CONTENT_URI;
			//content://com.sample.customprovider/
			break;
		default:
			throw new IllegalStateException("Unknown uri "+uri); 
		}
		
		return uriInserted.buildUpon().appendPath(String.valueOf(id)).build();
	}

	@Override
	public boolean onCreate() {
		database = new AppDatabaseHelper(getContext());
		
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Log.i("App","Content provider querying");
		SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
		int uriId = uriMatcher.match(uri);
			
		switch ( uriId ){
		case PERSONA:
			
			builder.setTables("personas");
			break;
		case PERSONA_ID:
			builder.setTables("personas");
			//content://com.sample.provider/persona/20
			builder.appendWhere("_ID = " + uri.getLastPathSegment());
			break;
		default:
			throw new IllegalStateException("Content cannot be resolved "+uri.toString());
			
		}
		
		SQLiteDatabase db = database.getReadableDatabase();
		Cursor cursor = builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
		
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		
		int uriId = uriMatcher.match(uri);
		SQLiteDatabase db = database.getWritableDatabase();
		int rowsUpdate = 0;
		switch(uriId){
		case PERSONA:
			rowsUpdate = db.update("personas", values, selection, selectionArgs);
			break;
		case PERSONA_ID:
			String id = uri.getLastPathSegment();
			if(TextUtils.isEmpty(selection)){
				rowsUpdate = db.update("personas", values," _ID = " + uri.getLastPathSegment(), selectionArgs);
			} else {
				rowsUpdate = db.update("personas", values, "_ID = " + uri.getLastPathSegment() + "and" + selection, selectionArgs);
			}
			break;
			default:
				throw new IllegalStateException("Unknown uri "+uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		
		return rowsUpdate;
	}

}
