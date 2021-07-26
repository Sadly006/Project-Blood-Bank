package Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.RequestAdapter;
import DataModels.RequestDataModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<RequestDataModel> requestDataModels;
    private RequestAdapter requestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.search_button) {

                    startActivity(new Intent(MainActivity.this, SearchActivity.class));
                }
                return false;
            }
        });

        requestDataModels = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        requestAdapter = new RequestAdapter(requestDataModels, this);
        recyclerView.setAdapter(requestAdapter);
        homePageItemShow();

    }

    private void homePageItemShow() {
        RequestDataModel a = new RequestDataModel("Taosif Sadly, O+");
        RequestDataModel b = new RequestDataModel("Taosif Sadly, O-");
        RequestDataModel c = new RequestDataModel("Taosif, A+");
        RequestDataModel d = new RequestDataModel("Sadly, B+");
        requestDataModels.add(a);
        requestDataModels.add(b);
        requestDataModels.add(c);
        requestDataModels.add(d);
        requestAdapter.notifyDataSetChanged();
    }
}