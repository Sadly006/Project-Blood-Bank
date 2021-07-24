package Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.bloodbank.R;

import java.util.HashMap;
import java.util.Map;

import Utils.singleton;

public class LoginActivity extends AppCompatActivity {
    EditText contactEt, passwordEt;
    Button submitButton;
    TextView signUpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        contactEt = findViewById(R.id.number);
        passwordEt = findViewById(R.id.password);
        submitButton = findViewById(R.id.submit_button);
        signUpText = findViewById(R.id.sign_up_text);
        signUpText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegActivity.class));
            }
        });
        submitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                contactEt.setError(null);
                passwordEt.setError(null);
                String contact, password;
                contact = contactEt.getText().toString();
                password = passwordEt.getText().toString();
                if (isValid(contact, password)) {
                    login(contact, password);
                }
            }
        });

    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    private boolean isValid(String contact, String password) {
        if (contact.isEmpty()) {
            showMessage("Name is empty");
            return false;
        } else if (password.isEmpty()) {
            showMessage("Password is required");
            return false;
        }

        return true;

    }

    private void login(final String contact, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sadly007.000webhostapp.com/login.php", new Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Success")) {
                    Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    LoginActivity.this.finish();
                } else {
                    Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Something went wrong :(", Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("contact", contact);
                params.put("password", password);
                return params;
            }
        };
        singleton.getInstance(this).addToRequestQueue(stringRequest);
    }

}