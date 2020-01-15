package dev.restuhaqza.country;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
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
    }


}
