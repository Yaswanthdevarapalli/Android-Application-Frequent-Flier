package com.example.frequentflier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent=getIntent();
        String pid=intent.getStringExtra("pid");

        TextView header=findViewById(R.id.textView6);
        TextView data=findViewById(R.id.textView10);

        String head="Flight Id\t\t\tFlight Miles\t\t\tDestination";
        header.setText(head);
        String url="http://10.0.2.2:8080/frequentflier/Flights.jsp?pid="+pid;
        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                String output=response.trim().replace(",","\t\t\t").replace("#","\n");
//                data.setText(output);
                String output="";
                String result=response.trim();
                String[] rows=result.split("#");
                for(int i=0;i<rows.length;i++)
                {
                    String row=rows[i].replace(",","\t\t\t\t\t\t");
                    output+=row+"\n";
                }
                output+="---------------------------------------------------------------------------";
                data.setText(output);
            }
        },null);
        queue.add(request);
    }
}