package com.example.customsyncadapter.db;

import android.content.ContentResolver;
import android.net.Uri;

public class AppContract {

	public static final String AUTHORITY = "com.sample.customprovider";
	
	public static final String BASE_PATH = "personas";
	public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY +"/" + BASE_PATH);

	//same as vnd.android.cursor.dir
	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/personas";

	//same as vnd.android.cursor.item
	public static final String CONTENT_TYPE_ITEM = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/personas";
}
