package com.example.saran.insertdata2_volley;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {


    // Creating EditText.
    EditText mName, mEmail, mGender;
    // Creating button;
    Button InsertButton;
    // Creating Volley RequestQueue.
    //RequestQueue requestQueue;
    // Create string variable to hold the EditText Value.
    String name, email, gender;
    // Creating Progress dialog.
    ProgressDialog progressDialog;
    // Storing server url into String variable.
    String HttpUrl = "https://siva123.000webhostapp.com/php/insert.php";
    //StringRequest stringRequest;

    StringRequest stringRequest;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assigning ID's to EditText.
        mName = (EditText) findViewById(R.id.editText_name);
        mEmail = (EditText) findViewById(R.id.editText_email);
        mGender = (EditText) findViewById(R.id.editText_gender);

        // Assigning ID's to Button.
        InsertButton = (Button) findViewById(R.id.insertbutton);

        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(MainActivity.this);

        progressDialog = new ProgressDialog(MainActivity.this);

        requestQueue = Volley.newRequestQueue(MainActivity.this);

        InsertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getvaluesfromedittext();
                progressDialog.setMessage("Please Wait, iserting Data");
                progressDialog.show();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                // Showing response message coming from server.
                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                // Showing error message if something goes wrong.
                                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        // return super.getParams();
                        // Creating Map String Params.
                        Map<String, String> params = new HashMap<String, String>();

                        // Adding All values to Params.
                        params.put("name", name);
                        params.put("email", email);
                        params.put("gender", gender);

                        return params;
                    }
                };

                // Creating RequestQueue.
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

                // Adding the StringRequest object into requestQueue.
                requestQueue.add(stringRequest);
            }

        });
    }


    private void getvaluesfromedittext() {
        //String name,email,gender;
        name = mName.getText().toString().trim();
        email = mEmail.getText().toString().trim();
        gender = mGender.getText().toString().trim();

        mName.setText("");
        mEmail.setText("");
        mGender.setText("");
    }
}
