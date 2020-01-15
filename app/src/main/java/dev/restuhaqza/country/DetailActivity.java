package dev.restuhaqza.country;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import dev.restuhaqza.country.Database.DatabaseContract;
import dev.restuhaqza.country.Model.CountryModel;

public class DetailActivity extends AppCompatActivity {

    private String alpha_code;
    private CountryModel countryModel;
    private ImageView flag;
    private TextView country_name;
    private TextView region;
    private TextView subregion;
    private TextView area;
    private TextView lang;
    private TextView currencyCode;
    private TextView currencyName;

    Button btn_favorite;


    private Boolean isFavorite = false;
    private FavHelper favHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_detail);
        countryModel = getIntent().getParcelableExtra("COUNTRY");

        favHelper = FavHelper.getInstance(getApplicationContext());
        favHelper.open();

        flag = findViewById(R.id.flag_detail);
        country_name = findViewById(R.id.name_country);
        region = findViewById(R.id.name_region);
        subregion = findViewById(R.id.subregion);
        area = findViewById(R.id.area);
        lang = findViewById(R.id.language);
        currencyCode = findViewById(R.id.currency_code);
        currencyName = findViewById(R.id.currency_name);
        btn_favorite =findViewById(R.id.btn_favorite);

        country_name.setText(countryModel.getName());
        region.setText(countryModel.getRegion());
        subregion.setText(countryModel.getSubRegion());
        area.setText(String.valueOf(countryModel.getArea()));
        lang.setText(countryModel.getNativeLang());
        currencyCode.setText(countryModel.getCurrencyCode());
        currencyName.setText(countryModel.getCurrencyName());

        Glide.with(this)
                .load(countryModel.getFlagPng())
                .into(flag);

        cekSQLiteFav();

        btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFavorite) {
                    FavRemove();
                }
                else{
                    FavSave();

                }

                isFavorite = !isFavorite;
                favBtnSet();
            }
        });
    }

    private void cekSQLiteFav() {

        Cursor cursor = favHelper.queryById(countryModel.getAlpha3code());

        if (cursor != null) {
            if (cursor.moveToFirst()) isFavorite = true;
            cursor.close();
        }

        favBtnSet();
    }

    private void favBtnSet() {
        if (isFavorite) {
            btn_favorite.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favorite,0,0,0);
        }
        else {
            btn_favorite.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favorite_off,0,0,0);
        }
    }

    private void FavSave() {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseContract.Country_Columns.alpha2code, countryModel.getAlpha2code());
        cv.put(DatabaseContract.Country_Columns.alpha3code, countryModel.getAlpha3code());
        cv.put(DatabaseContract.Country_Columns.name, countryModel.getName());
        cv.put(DatabaseContract.Country_Columns.nativeName, countryModel.getNativeName());
        cv.put(DatabaseContract.Country_Columns.region, countryModel.getRegion());
        cv.put(DatabaseContract.Country_Columns.subRegion, countryModel.getSubRegion());
        cv.put(DatabaseContract.Country_Columns.latitude, countryModel.getLatitude());
        cv.put(DatabaseContract.Country_Columns.longitude, countryModel.getLongitude());
        cv.put(DatabaseContract.Country_Columns.area, countryModel.getArea());
        cv.put(DatabaseContract.Country_Columns.numericCode, countryModel.getNumericCode());
        cv.put(DatabaseContract.Country_Columns.nativeLang, countryModel.getNativeLang());
        cv.put(DatabaseContract.Country_Columns.currencyCode, countryModel.getCurrencyCode());
        cv.put(DatabaseContract.Country_Columns.currencyName, countryModel.getCurrencyName());
        cv.put(DatabaseContract.Country_Columns.flag, countryModel.getFlagPng());
        favHelper.insert(cv);

        Toast.makeText(this, R.string.toast_save, Toast.LENGTH_SHORT).show();
    }

    private void FavRemove() {
        favHelper.delete(countryModel.getAlpha3code());
        Toast.makeText(this, R.string.toast_remove, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        favHelper.close();
    }

}

