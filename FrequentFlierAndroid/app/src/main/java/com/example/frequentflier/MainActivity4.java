package com.example.frequentflier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        setContentView(R.layout.activity_main4);
        Intent intent=getIntent();
        String pid=intent.getStringExtra("pid");

        Spinner spinner=findViewById(R.id.spinner);
        TextView depart_textview=findViewById(R.id.textView12);
        TextView arv_textview=findViewById(R.id.textView13);
        TextView miles_textview=findViewById(R.id.textView14);
        TextView trip_textview=findViewById(R.id.textView17);

        ArrayList<String> data=new ArrayList<String>();
        RequestQueue queue= Volley.newRequestQueue(this);
        String url="http://10.0.2.2:8080/frequentflier/Flights.jsp?pid="+pid;
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] output=response.trim().split("#");
                for (int i=0;i<output.length;i++)
                {
                    String[] col=output[i].trim().split(",");
                    data.add(col[0]);
                }
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity4.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,data);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }
        },null);
        queue.add(request);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String flight_id=adapterView.getSelectedItem().toString();
                String url2="http://10.0.2.2:8080/frequentflier/FlightDetails.jsp?flightid="+flight_id;
                StringRequest request1=new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String[] rows=response.trim().split("#");
                        String data="";

                        for(int i=0;i< rows.length;i++)
                        {
                            if(i==0)
                            {
                                String[] row1=rows[i].trim().split(",");
                                depart_textview.setText("Departure: "+row1[1]);
                                arv_textview.setText("Arrival: "+row1[0]);
                                miles_textview.setText("Miles: "+row1[2]);
                            }
                            String details="";
                            for(int j=0;j<rows.length;j++)
                            {
                                String[] values=rows[j].trim().split(",");
                                details +="\t\t\t\t\t\t"+values[3]+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+values[4]+"\n";
                            }
                            trip_textview.setText(details);

                        }
                    }
                },null);
                queue.add(request1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}