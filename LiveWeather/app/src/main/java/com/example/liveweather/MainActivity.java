package com.example.liveweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText srch;
    TextView txtview,temp,hty,mintmp,mxtemp,sr,ss;
    String key = "b74fb498267b52e414def66dd4303fb1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         srch = findViewById(R.id.search);
         txtview = findViewById(R.id.cityname);
         temp = findViewById(R.id.temp);
         hty = findViewById(R.id.humidity);
         mintmp = findViewById(R.id.mintemp);
         mxtemp=findViewById(R.id.maxtemp);
         sr= findViewById(R.id.sunrise);
         ss= findViewById(R.id.sunset);
        }
        public void get(View v){
            String key = "b74fb498267b52e414def66dd4303fb1";
            String city = srch.getText().toString();
            String url= "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=b74fb498267b52e414def66dd4303fb1";
            RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                        try {
                            JSONObject object = response.getJSONObject("main");
                            JSONObject object2 = response.getJSONObject("sys");
                            String Temprature = object.getString("temp");
                            String humidity = object.getString("humidity");
                            String mxtp = object.getString("temp_max");
                            String mintp = object.getString("temp_min");
                            String sunst = object2.getString("sunset").toString();
                            String ssr = object2.getString("sunrise").toString();

                            temp.setText("Temprature: "+Temprature);
                            txtview.setText(city);
                            hty.setText(humidity);
                            mintmp.setText(mintp);
                            mxtemp.setText(mxtp);
                            ss.setText(sunst);
                            sr.setText(ssr);



                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.toString(),Toast.LENGTH_SHORT).show();
                }
            });

            queue.add(request);
        }



}