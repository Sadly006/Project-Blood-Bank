package Activities;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
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

import Utils.singleton;

public class RegActivity extends AppCompatActivity {
    private EditText nameEt, groupEt, contactEt, locationEt, passwordEt;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        nameEt = findViewById(R.id.name);
        groupEt = findViewById(R.id.group);
        contactEt = findViewById(R.id.contact);
        locationEt = findViewById(R.id.location);
        passwordEt = findViewById(R.id.password);
        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, location, group, password, contact;
                name = nameEt.getText().toString();
                location = locationEt.getText().toString();
                group = groupEt.getText().toString();
                contact = contactEt.getText().toString();
                password = passwordEt.getText().toString();
                if (isValid(name, location, group, password, contact)) {
                    register(name, location, group, password, contact);
                    System.out.println("ab");
                    startActivity(new Intent(RegActivity.this, MainActivity.class));
                }

            }
        });

    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    private boolean isValid(String name, String location, String group, String password,
                            String contact) {
        List<String> validBloodGroups = new ArrayList<>();
        validBloodGroups.add("A+");
        validBloodGroups.add("A-");
        validBloodGroups.add("B+");
        validBloodGroups.add("B-");
        validBloodGroups.add("AB+");
        validBloodGroups.add("AB-");
        validBloodGroups.add("O+");
        validBloodGroups.add("O-");
        if (name.isEmpty()) {
            showMessage("Name is empty");
            return false;
        } else if (location.isEmpty()) {
            showMessage("Location is required");
            return false;
        } else if (!validBloodGroups.contains(group)) {
            showMessage("Blood group invalid choose from " + validBloodGroups);
            return false;
        } else if (contact.length() != 11) {
            showMessage("Invalid mobile number, number should be of 11 digits");
            return false;
        } else if (password.isEmpty()) {
            showMessage("Password is required");
            return false;
        }

        return true;

    }

    private void register(final String name, final String location, final String group, final String password,
                          final String contact) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "", new Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("Success")){
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit()
                            .putString("location", location).apply();
                    Toast.makeText(RegActivity.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegActivity.this, MainActivity.class));
                    RegActivity.this.finish();
                }else{
                    Toast.makeText(RegActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegActivity.this, "Something went wrong:(", Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("location", location);
                params.put("blood_group", group);
                params.put("password", password);
                params.put("number", contact);
                return params;
            }
        };
        singleton.getInstance(this).addToRequestQueue(stringRequest);
    }

}