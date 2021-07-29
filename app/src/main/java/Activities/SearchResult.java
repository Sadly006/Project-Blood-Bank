package Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import Adapter.SearchAdapter;
import DataModels.Donor;

public class SearchResult extends AppCompatActivity {
    List<Donor> donorList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        donorList = new ArrayList<>();
        String json;
        String location, bloodGroup;
        Intent intent = getIntent();
        json = intent.getStringExtra("json");
        location = intent.getStringExtra("chooseLocation");
        bloodGroup = intent.getStringExtra("chooseGroup");

        Gson gson = new Gson();

        Type type = new TypeToken<List<Donor>>() {}.getType();
        List<Donor> dataModels = gson.fromJson(json, type);
        if (dataModels != null) {
            donorList.addAll(dataModels);
        }

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        SearchAdapter adapter = new SearchAdapter(donorList, SearchResult.this);
        recyclerView.setAdapter(adapter);


    }
}