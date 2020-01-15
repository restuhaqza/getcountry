package dev.restuhaqza.country.Database;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_COUNTRY = "country";
    public static final class Country_Columns implements BaseColumns {
        public static String name = "name";
        public static String alpha2code = "alpha2code";
        public static String alpha3code = "alpha3code";
        public static String nativeName = "nativeName";
        public static String region = "region";
        public static String subRegion = "subRegion";
        public static String latitude = "latitude";
        public static String longitude = "longitude";
        public static String area = "area";
        public static String numericCode = "numericCode";
        public static String nativeLang = "nativeLang";
        public static String currencyCode = "currencyCode";
        public static String currencyName = "currencyName";
    }
}
