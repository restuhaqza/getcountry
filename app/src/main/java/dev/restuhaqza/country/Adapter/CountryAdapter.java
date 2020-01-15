package dev.restuhaqza.country.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import dev.restuhaqza.country.DetailActivity;
import dev.restuhaqza.country.Model.CountryModel;
import dev.restuhaqza.country.R;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder>{
    private ArrayList<CountryModel> mData = new ArrayList<>();

    public void setData(ArrayList<CountryModel> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new CountryViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder CountryViewHolder, int position) {
        CountryViewHolder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {
        ImageView flag;
        TextView name_country;
        LinearLayout cv_list;
        String alphaCode;

        CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            flag = itemView.findViewById(R.id.flag_country);
            name_country = itemView.findViewById(R.id.name_country);
            cv_list = itemView.findViewById(R.id.cv_list);

        }

        void bind(final CountryModel countryList) {

            name_country.setText(countryList.getName());
            alphaCode = countryList.getAlpha3code();
            Glide.with(itemView.getContext())
                    .load(countryList.getFlagPng())
                    .into(flag);

            cv_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                    intent.putExtra("COUNTRY", countryList);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
