package dev.restuhaqza.country.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME         = "db_country";
    private static final int DATABASE_VERSION   = 1;
    private static final String SQL_CREATE_TABLE_COUNTRY = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_COUNTRY,
            DatabaseContract.Country_Columns._ID,
            DatabaseContract.Country_Columns.name,
            DatabaseContract.Country_Columns.alpha2code,
            DatabaseContract.Country_Columns.alpha3code,
            DatabaseContract.Country_Columns.nativeName,
            DatabaseContract.Country_Columns.region,
            DatabaseContract.Country_Columns.subRegion,
            DatabaseContract.Country_Columns.latitude,
            DatabaseContract.Country_Columns.longitude,
            DatabaseContract.Country_Columns.area,
            DatabaseContract.Country_Columns.numericCode,
            DatabaseContract.Country_Columns.nativeLang,
            DatabaseContract.Country_Columns.currencyCode,
            DatabaseContract.Country_Columns.currencyName
    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_COUNTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
