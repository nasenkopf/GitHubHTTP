package com.jrgliese.githubhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<String> where;
    ListView mListView;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mListView = (ListView) findViewById(R.id.listview);

        where = new ArrayList<String>();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();


        ApiInterace client = retrofit.create(ApiInterace.class);
        Call<List<Organization>> call = (Call<List<Organization>>) client.orgList();

        call.enqueue(new Callback<List<Organization>>() {
            @Override
            public void onResponse(Call<List<Organization>> call, Response<List<Organization>> response) {
                List<Organization> repos = response.body();
                for (int i = 0; i < 30; i++) {
                    Log.d("Name", response.body().get(i).getLogin());
                    if (repos.get(i).getDescription() != null && !repos.get(i).getDescription().toString().equals("")) {
                        Log.d("Beschreibung", response.body().get(i).getDescription());
                        where.add(repos.get(i).getLogin()+": \n"+repos.get(i).getDescription());
                    }else{
                        where.add(repos.get(i).getLogin());
                    }
                }
                adapter = new ArrayAdapter<String>(getApplication(), android.R.layout.simple_list_item_1, where);
                mListView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Organization>> call, Throwable t) {

            }
        });

    }
}
