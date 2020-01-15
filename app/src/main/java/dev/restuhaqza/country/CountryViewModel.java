package dev.restuhaqza.country;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import dev.restuhaqza.country.Model.CountryModel;

public class CountryViewModel extends ViewModel {
    private MutableLiveData<ArrayList<CountryModel>> countryLists = new MutableLiveData<>();

    void setCountryList(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<CountryModel> listItems = new ArrayList<>();
        String url = "http://pcs-country.herokuapp.com/data/country";
        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("result");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject countryItem = list.getJSONObject(i);
                        CountryModel countryList = new CountryModel();
                        countryList.setAlpha2code(countryItem.getString("Alpha2Code"));
                        countryList.setAlpha3code(countryItem.getString("Alpha3Code"));
                        countryList.setName(countryItem.getString("Name"));
                        countryList.setNativeName(countryItem.getString("NativeName"));
                        countryList.setRegion(countryItem.getString("Region"));
                        countryList.setSubRegion(countryItem.getString("SubRegion"));
                        countryList.setNativeLang(countryItem.getString("NativeLanguage"));
                        countryList.setLatitude(countryItem.getString("Latitude"));
                        countryList.setLongitude(countryItem.getString("Longitude"));
                        countryList.setArea(Double.parseDouble(countryItem.getString("Area")));
                        countryList.setCurrencyCode(countryItem.getString("CurrencyCode"));
                        countryList.setCurrencyName(countryItem.getString("CurrencyName"));
                        countryList.setCurrencySymbol(countryItem.getString("CurrencySymbol"));
                        countryList.setNumericCode(Integer.parseInt(countryItem.getString("NumericCode")));
                        countryList.setFlag(countryItem.getString("Flag"));
                        countryList.setFlagPng(countryItem.getString("FlagPng"));
                        listItems.add(countryList);
                    }
                    countryLists.postValue(listItems);
                } catch (Exception e) {
                    e.getStackTrace();
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    LiveData<ArrayList<CountryModel>> getCountry() {
        return countryLists;
    }
}
