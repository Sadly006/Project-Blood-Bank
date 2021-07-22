package Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


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
                if (isValid(name, location, group, password, contact)) {

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
        } else if (contact.length() != 10) {
            showMessage("Invalid mobile number, number should be of 10 digits");
            return false;
        } else if (password.isEmpty()) {
            showMessage("Password is required");
            return false;
        }

        return true;

    }
}
