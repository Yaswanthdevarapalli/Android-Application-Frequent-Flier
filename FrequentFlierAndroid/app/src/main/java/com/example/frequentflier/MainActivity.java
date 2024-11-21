package com.example.frequentflier;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText usernameEditText=findViewById(R.id.usernameEditText);
        EditText passEditText=findViewById(R.id.passEditText);
        Button loginBtn=findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = usernameEditText.getText().toString();
                String pass= passEditText.getText().toString();
                String url="http://10.0.2.2:8080/frequentflier/login?user="+user+"&pass="+pass;

                RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
                StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        String result=s.trim();
                        if(result.contains("Yes")){
                            Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                            String[] auth=result.split(":");
                            String pid=auth[1];
                            intent.putExtra("pid",pid);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Incorrect Username/password",Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                    }
                });
                queue.add(request);

            }
        });
    }
}