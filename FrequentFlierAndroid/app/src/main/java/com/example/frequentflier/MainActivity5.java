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

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Intent intent=getIntent();
        String pid=intent.getStringExtra("pid");

        Spinner spinner2=findViewById(R.id.spinner2);
        TextView desc_textview=findViewById(R.id.textView20);
        TextView pt_textview=findViewById(R.id.textView22);
        TextView award_textview=findViewById(R.id.textView25);

        RequestQueue queue= Volley.newRequestQueue(this);
        String url="http://10.0.2.2:8080/frequentflier/AwardIds.jsp?pid="+pid;
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<String> list=new ArrayList<String>();
                String[] output=response.trim().split("#");
                for(int i=0;i<output.length;i++)
                {
                    list.add(output[i]);
                }
                ArrayAdapter<String> adapter2=new ArrayAdapter<String>(MainActivity5.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(adapter2);
            }
        },null);
        queue.add(request);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String awardid=adapterView.getSelectedItem().toString();
                String url2="http://10.0.2.2:8080/frequentflier/RedemptionDetails.jsp?awardid="+awardid+"&pid="+pid;
                StringRequest request2=new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String[] rows=response.trim().split("#");
                        String[] data=rows[0].trim().split(",");
                        desc_textview.setText(data[0]);
                        pt_textview.setText(data[1]);
                        String content="";
                        for(int i=0;i< rows.length;i++)
                        {
                            String[] values=rows[i].trim().split(",");
                            content+=values[2]+"\t\t\t"+values[3];
                        }
                        award_textview.setText(content);
                    }
                },null);
                queue.add(request2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}