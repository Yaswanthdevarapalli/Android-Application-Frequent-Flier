package com.example.frequentflier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity6 extends AppCompatActivity {
    String dpid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        setContentView(R.layout.activity_main6);
        String spid=intent.getStringExtra("pid");
        Spinner spinner=findViewById(R.id.spinner3);
        EditText pt_text=findViewById(R.id.editText);
        Button button=findViewById(R.id.button);

        RequestQueue queue= Volley.newRequestQueue(this);
        String url="http://10.0.2.2:8080/frequentflier/GetPassengerids.jsp?pid="+spid;
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<String> newlist=new ArrayList<String>();
                String[] rows=response.trim().split("#");
                for(int i=0;i< rows.length;i++)
                {
                    newlist.add(rows[i]);
                }
                ArrayAdapter<String> newadapter=new ArrayAdapter<String>(MainActivity6.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,newlist);
                newadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(newadapter);
            }
        },null);
        queue.add(request);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dpid=adapterView.getSelectedItem().toString();
                System.out.println("onItemSelected:" + dpid);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int points=Integer.parseInt(pt_text.getText().toString());

                String url2="http://10.0.2.2:8080/frequentflier/TransferPoints.jsp?spid="+spid+"&dpid="+dpid+"&npoints="+points;
                StringRequest request1=new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity6.this,points+"Points Transferred successfully",Toast.LENGTH_LONG).show();
                    }
                },null);
                queue.add(request1);
            }
        });
    }
}