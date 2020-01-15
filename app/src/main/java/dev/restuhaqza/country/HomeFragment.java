package dev.restuhaqza.country;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ProgressBar;

import java.util.ArrayList;

import dev.restuhaqza.country.Adapter.CountryAdapter;
import dev.restuhaqza.country.Model.CountryModel;


public class HomeFragment extends Fragment {
    private View view;
    private CountryAdapter adapter;
    private ProgressBar progressBar;

    private CountryViewModel countryViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_home, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        showLoading(true);

        adapter = new CountryAdapter();
        adapter.notifyDataSetChanged();

        RecyclerView rvPlaying = view.findViewById(R.id.rv_playing);
        rvPlaying.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvPlaying.setAdapter(adapter);
        rvPlaying.setHasFixedSize(true);

        showLoading(true);

        countryViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(CountryViewModel.class);
        countryViewModel.setCountryList();


        countryViewModel.getCountry().observe(this, new Observer<ArrayList<CountryModel>>() {
            @Override
            public void onChanged(ArrayList<CountryModel> countryLists) {
                if(countryLists != null){
                    adapter.setData(countryLists);
                    showLoading(false);
                }
            }
        });
        showLoading(true);
        return view;

    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}
