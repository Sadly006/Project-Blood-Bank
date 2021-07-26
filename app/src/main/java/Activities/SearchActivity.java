package Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.bloodbank.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import Utils.singleton;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final EditText groupEt, locationEt;
        groupEt = findViewById(R.id.chooseGroup);
        locationEt = findViewById(R.id.chooseLocation);
        Button submit_button = findViewById(R.id.submit_button);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bloodGroup = groupEt.getText().toString();
                String location = locationEt.getText().toString();
                if(isValid(bloodGroup, location)){
                    searchResults(bloodGroup, location);
                }
            }
        });
    }


    private void searchResults(final String bloodGroup, final String location) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, "", new Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent intent = new Intent(SearchActivity.this, SearchResult.class);
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SearchActivity.this, "Something went wrong :(", Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", Objects.requireNonNull(error.getMessage()));
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("chooseLocation", location);
                params.put("chooseGroup", bloodGroup);
                return params;
            }
        };
        singleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    private boolean isValid(String bloodGroup, String location){
        List<String> validBloodGroups = new ArrayList<>();
        validBloodGroups.add("A+");
        validBloodGroups.add("A-");
        validBloodGroups.add("B+");
        validBloodGroups.add("B-");
        validBloodGroups.add("AB+");
        validBloodGroups.add("AB-");
        validBloodGroups.add("O+");
        validBloodGroups.add("O-");
        if(!validBloodGroups.contains(bloodGroup)){
            showMsg("Blood group invalid choose from " + validBloodGroups);
            return false;
        }else if(location.isEmpty()){
            showMsg("Enter Location");
            return false;
        }
        return true;
    }


    private void showMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}