package Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.preference.PreferenceManager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.ErrorListener;

import com.example.bloodbank.R;

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

                }
            });

        }


    }
