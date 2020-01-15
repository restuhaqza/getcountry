package dev.restuhaqza.country;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.restuhaqza.country.Adapter.CountryAdapter;
import dev.restuhaqza.country.Database.DatabaseContract;
import dev.restuhaqza.country.FavHelper;
import dev.restuhaqza.country.Model.CountryModel;
import dev.restuhaqza.country.R;

public class FavoriteFragment extends Fragment {
    private Cursor list;
    private CountryAdapter adapter;
    private Context context;
    private RecyclerView rv_fav;

    private FavHelper favHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        context = view.getContext();

        rv_fav = view.findViewById(R.id.rv_fav);
        new loadDataAsync().execute();

        return view;
    }

    private class loadDataAsync extends AsyncTask<Void, Void, Cursor> {
        @Override
        protected Cursor doInBackground(Void... voids) {
            Log.d("MSG","doInBackground");
            favHelper = FavHelper.getInstance(getActivity().getApplicationContext());
            favHelper.open();

            list = favHelper.queryAll();

            return list;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);

            list = cursor;

            ArrayList<CountryModel> mArrayList = new ArrayList<CountryModel>();
            for(list.moveToFirst(); !list.isAfterLast(); list.moveToNext()) {

                CountryModel countryList = new CountryModel();


                countryList.setAlpha2code(list.getString(list.getColumnIndex(DatabaseContract.Country_Columns.alpha2code)));
                countryList.setAlpha3code(list.getString(list.getColumnIndex(DatabaseContract.Country_Columns.alpha3code)));
                countryList.setName(list.getString(list.getColumnIndex(DatabaseContract.Country_Columns.name)));
                countryList.setNativeName(list.getString(list.getColumnIndex(DatabaseContract.Country_Columns.nativeName)));
                countryList.setRegion(list.getString(list.getColumnIndex(DatabaseContract.Country_Columns.region)));
                countryList.setSubRegion(list.getString(list.getColumnIndex(DatabaseContract.Country_Columns.subRegion)));
                countryList.setLatitude(list.getString(list.getColumnIndex(DatabaseContract.Country_Columns.latitude)));
                countryList.setLongitude(list.getString(list.getColumnIndex(DatabaseContract.Country_Columns.longitude)));
                countryList.setArea(Double.parseDouble(list.getString(list.getColumnIndex(DatabaseContract.Country_Columns.area))));
                countryList.setNumericCode(Integer.parseInt(list.getString(list.getColumnIndex(DatabaseContract.Country_Columns.numericCode))));
                countryList.setNativeLang(list.getString(list.getColumnIndex(DatabaseContract.Country_Columns.nativeLang)));
                countryList.setCurrencyCode(list.getString(list.getColumnIndex(DatabaseContract.Country_Columns.currencyCode)));
                countryList.setCurrencyName(list.getString(list.getColumnIndex(DatabaseContract.Country_Columns.currencyName)));
                countryList.setFlagPng(list.getString(list.getColumnIndex(DatabaseContract.Country_Columns.flag)));

                mArrayList.add(countryList);
            }

            adapter = new CountryAdapter();
            adapter.setData(mArrayList);
            rv_fav.setLayoutManager(new LinearLayoutManager(context));
            rv_fav.setAdapter(adapter);
        }
    }


    @Override
    public void onDestroyView() {
        favHelper.close();
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        new loadDataAsync().execute();
    }
}
