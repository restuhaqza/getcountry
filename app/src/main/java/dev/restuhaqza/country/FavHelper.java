package dev.restuhaqza.country;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import dev.restuhaqza.country.Database.DatabaseHelper;

import static android.provider.BaseColumns._ID;
import static dev.restuhaqza.country.Database.DatabaseContract.Country_Columns.alpha3code;
import static dev.restuhaqza.country.Database.DatabaseContract.TABLE_COUNTRY;

public class FavHelper {
    private static String DATABASE_TABLE = TABLE_COUNTRY;
    private Context context;
    private DatabaseHelper dataBaseHelper;
    private static FavHelper INSTANCE;
    private SQLiteDatabase database;

    private FavHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public static FavHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FavHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }

    public void close() {
        dataBaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    public Cursor queryAll(){
        Cursor cursor = database.query(DATABASE_TABLE
                ,null
                ,null
                ,null
                ,null
                ,null,_ID +" DESC"
                ,null);

        return cursor;
    }

    public Cursor queryById(String id){
        Cursor cursor = database.query(DATABASE_TABLE
                ,null
                ,alpha3code + " = ?"
                ,new String[]{id}
                ,null
                ,null,_ID +" DESC"
                ,null);

        return cursor;
    }

    public long insert(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    public int delete(int id){
        return database.delete(TABLE_COUNTRY, alpha3code + " = '"+id+"'", null);
    }

}
