package com.example.frequentflier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent=getIntent();
        String pid=intent.getStringExtra("pid");

        TextView textView4=findViewById(R.id.textView4);
        TextView textview7=findViewById(R.id.textView7);
        ImageView imageView=findViewById(R.id.imageView);
        Button flight=findViewById(R.id.button6);
        Button all_flight=findViewById(R.id.button7);
        Button red_info=findViewById(R.id.button8);
        Button transfer=findViewById(R.id.button9);
        Button exit=findViewById(R.id.button10);

        String url="http://10.0.2.2:8080/frequentflier/Info.jsp?pid="+pid;
        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String[] output=s.trim().split(",");
                String name=output[0];
                String points=output[1];
                textView4.setText(name);
                textview7.setText(points);

            }
        },null);
        queue.add(request);
        String url2="http://10.0.2.2:8080/frequentflier/Images/"+pid+".jpeg";
        ImageRequest request2=new ImageRequest(url2, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imageView.setImageBitmap(response);
            }
        },0,0,null,null);
        queue.add(request2);

        flight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent flight_intent=new Intent(MainActivity2.this,MainActivity3.class);
                flight_intent.putExtra("pid",pid);
                startActivity(flight_intent);
            }
        });

        all_flight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent all_flight_intent=new Intent(MainActivity2.this,MainActivity4.class);
                all_flight_intent.putExtra("pid",pid);
                startActivity(all_flight_intent);
            }
        });

        red_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent redemp_intent=new Intent(MainActivity2.this,MainActivity5.class);
                redemp_intent.putExtra("pid",pid);
                startActivity(redemp_intent);
            }
        });

        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent transfer_intent=new Intent(MainActivity2.this, MainActivity6.class);
                transfer_intent.putExtra("pid",pid);
                startActivity(transfer_intent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exit_intent=new Intent(MainActivity2.this, MainActivity.class);
                startActivity(exit_intent);
            }
        });

    }
}